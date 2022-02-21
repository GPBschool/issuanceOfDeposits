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
    public void depositFounds(DepositFoundsRequest depositFoundsRequest)
            throws DepositException, NotFoundException {
        DepositEntity depositEntity = getDepositByAccountNumber(depositFoundsRequest.getAccountNumber());
        permissionCheck(depositEntity);
        externalServices.makeTransver(depositFoundsRequest.getClientId(),
                "", depositFoundsRequest.getAccountNumber(), depositFoundsRequest.getAmmountDeposit());

        depositEntity.setAmmountDeposit(depositEntity.getAmmountDeposit() + depositFoundsRequest.getAmmountDeposit());
        depositRepository.save(depositEntity);
    }

    // добавление нового депозита
    public DepositDto addDeposit(DepositRequest depositRequest) throws NotFoundException, DepositException {

        String accountNumber = externalServices.createADepositAccount(depositRequest.getClientId());
        externalServices.makeTransver(depositRequest.getClientId(), "",
                accountNumber, depositRequest.getAmmountDeposit());
        ConditionsEntity conditionsEntity = conditionsService.findById(depositRequest.getConditionsId());
        DepositEntity depositEntity = mapperDeposit.depositRequestToEntity(depositRequest, accountNumber, conditionsEntity);
        depositRepository.save(depositEntity);

        return mapperDeposit.depositEntityToDto(depositEntity);
    }


    // закрытие депозита
    public void closeDeposit(String accountNumber) throws NotFoundException, DepositException {
        DepositEntity depositEntity = getDepositByAccountNumber(accountNumber);
        permissionCheck(depositEntity);
        externalServices.makeTransver(depositEntity.getClientId(),
                accountNumber, "", depositEntity.getAmmountPercent() + depositEntity.getAmmountDeposit());

        depositEntity.setAmmountDeposit(0.);
        depositEntity.setAmmountPercent(0.);
        depositEntity.setIsClosed(true);
        depositRepository.save(depositEntity);
    }

    // перевод процентов с депозита
    public void withdrawDeposit(String accountNumber) throws NotFoundException, DepositException {
        DepositEntity depositEntity = getDepositByAccountNumber(accountNumber);
        externalServices.makeTransver(depositEntity.getClientId(),
                accountNumber, "", depositEntity.getAmmountPercent());

        depositEntity.setAmmountPercent(0.);
        depositRepository.save(depositEntity);
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
}
