package ru.gpb.school.issuanceofdeposits.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gpb.school.issuanceofdeposits.api.request.DepositFoundsRequest;
import ru.gpb.school.issuanceofdeposits.api.request.DepositRequest;
import ru.gpb.school.issuanceofdeposits.exception.DepositException;
import ru.gpb.school.issuanceofdeposits.exception.NotFoundException;
import ru.gpb.school.issuanceofdeposits.model.dto.DepositDto;
import ru.gpb.school.issuanceofdeposits.model.entity.ConditionsEntity;
import ru.gpb.school.issuanceofdeposits.model.entity.DepositEntity;
import ru.gpb.school.issuanceofdeposits.model.mapper.MapperDeposit;
import ru.gpb.school.issuanceofdeposits.repository.DepositRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DepositService {
    private final DepositRepository depositRepository;
    private final ConditionsService conditionsService;
    private final ExternalServices externalServices;
    private final MapperDeposit mapperDeposit;

    // список депозитов по клиенту
    public Collection<DepositDto> findDepositByClientId(int clientId) {
        Collection<DepositEntity> depositEntities = depositRepository
                .findDepositEntityByClientIdAndIsClosedFalse(clientId);
        return depositEntities.stream().map(mapperDeposit::depositEntityToDto).toList();
    }

    // пополнение депозита
    public DepositDto depositFounds(DepositFoundsRequest depositFoundsRequest)
            throws DepositException, NotFoundException {
        DepositEntity depositEntity = getDepositByAccountNumber(depositFoundsRequest.getAccountNumber());
        permissionCheck(depositEntity);
        externalServices.makeTransver(depositFoundsRequest.getClientId(),
                "", depositFoundsRequest.getAccountNumber(), depositFoundsRequest.getAmountDeposit());

        depositEntity.setAmountDeposit(depositEntity.getAmountDeposit() + depositFoundsRequest.getAmountDeposit());
        depositRepository.save(depositEntity);
        return mapperDeposit.depositEntityToDto(depositEntity);
    }

    // добавление нового депозита
    public DepositDto addDeposit(DepositRequest depositRequest) throws NotFoundException, DepositException {

        String accountNumber = externalServices.createADepositAccount(depositRequest.getClientId());
        externalServices.makeTransver(depositRequest.getClientId(), "",
                accountNumber, depositRequest.getAmountDeposit());
        ConditionsEntity conditionsEntity = conditionsService.findById(depositRequest.getConditionsId());
        DepositEntity depositEntity = mapperDeposit.depositRequestToEntity(depositRequest, accountNumber, conditionsEntity);
        depositRepository.save(depositEntity);

        return mapperDeposit.depositEntityToDto(depositEntity);
    }


    // закрытие депозита
    public DepositDto closeDeposit(String accountNumber) throws NotFoundException, DepositException {
        DepositEntity depositEntity = getDepositByAccountNumber(accountNumber);
        depositEntity.setIsClosed(true);
        depositRepository.save(depositEntity);
        double amountDeposit = depositEntity.getAmountDeposit();
        double amountPercent = depositEntity.getAmountPercent();
        double amount = amountPercent + amountDeposit;
        externalServices.makeTransver(depositEntity.getClientId(), accountNumber, "", amount);
        depositEntity.setAmountDeposit(0.);
        depositEntity.setAmountPercent(0.);
        depositRepository.save(depositEntity);
        return mapperDeposit.depositEntityToDto(depositEntity);
    }

    // перевод процентов с депозита
    public DepositDto withdrawDeposit(String accountNumber) throws NotFoundException, DepositException {
        DepositEntity depositEntity = getDepositByAccountNumber(accountNumber);
        externalServices.makeTransver(depositEntity.getClientId(),
                accountNumber, "", depositEntity.getAmountPercent());

        depositEntity.setAmountPercent(0.);
        depositRepository.save(depositEntity);
        return mapperDeposit.depositEntityToDto(depositEntity);
    }

    // проверка условий депозита
    private void permissionCheck(DepositEntity depositEntity) throws DepositException {
        if (depositEntity.getIsClosed()) {
            throw new DepositException("Deposit is closed");
        }
        if (depositEntity.getConditions().isReplenishment()) {
            throw new DepositException("Replenishment is prohibited");
        }
    }

    // получить депозит по номеру счета
    private DepositEntity getDepositByAccountNumber(String accountNumber) throws NotFoundException {
        return depositRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundException("Deposit by account number " +
                        accountNumber + " not found"));
    }

    // расчет процентов
    public void calculatePercents(LocalDateTime now) throws NotFoundException, DepositException {
        Collection<DepositEntity> depositEntities = depositRepository.findDepositEntityByIsClosedFalse();
        for (DepositEntity depositEntity : depositEntities) {
            calc(depositEntity, now);
        }
    }

    private void calc(DepositEntity depositEntity, LocalDateTime now) throws NotFoundException, DepositException {
        long intervalInSeconds = depositEntity.getConditions().getTermOfDeposit().getSeconds();
        long difference = Duration.between(depositEntity.getDepositOpeningDate(), now).getSeconds() + 1;
        double currentCalculation = (depositEntity.getConditions().getPercent() * depositEntity.getAmountDeposit()) / 100;
        depositEntity.setAmountPercent(depositEntity.getAmountPercent() + currentCalculation);
        depositEntity.setDateOfLastPercentCalculation(now);
        depositRepository.save(depositEntity);
        if (difference >= intervalInSeconds) {
            closeDeposit(depositEntity.getAccountNumber());
        }
    }
}
