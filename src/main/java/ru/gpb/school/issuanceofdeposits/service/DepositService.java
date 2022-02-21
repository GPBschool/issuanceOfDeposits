package ru.gpb.school.issuanceofdeposits.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gpb.school.issuanceofdeposits.api.request.DepositRequest;
import ru.gpb.school.issuanceofdeposits.exception.NotFoundException;
import ru.gpb.school.issuanceofdeposits.model.dto.DepositDto;
import ru.gpb.school.issuanceofdeposits.model.entity.ConditionsEntity;
import ru.gpb.school.issuanceofdeposits.model.entity.DepositEntity;
import ru.gpb.school.issuanceofdeposits.model.mapper.MapperDeposit;
import ru.gpb.school.issuanceofdeposits.repository.DepositRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepositService {
    private final DepositRepository depositRepository;
    private final ConditionsService conditionsService;
    private final ExternalServices externalServices;
    private final MapperDeposit mapperDeposit;
//    public Collection<DepositDTO> findByClientId(int clientId) {
//        return depositRepository.findByClientId(clientId);
//    }
//
//    public DepositDTO findById(int id) throws NotFoundException {
//        return depositRepository.findById(id);
//    }

    public DepositDto addDeposit(DepositRequest depositRequest) throws NotFoundException {

        String accountNumber = externalServices.createADepositAccount(depositRequest.getClientId());
        boolean resultMakeTransver = externalServices.makeTransver(depositRequest.getClientId(), "",
                accountNumber, depositRequest.getAmmountDeposit());

        if (resultMakeTransver) {
            DepositEntity depositEntity = new DepositEntity();
            depositEntity.setClientId(depositRequest.getClientId());
            depositEntity.setAccountNumber(accountNumber);
            depositEntity.setAmmountDeposit(depositRequest.getAmmountDeposit());
            depositEntity.setDepositOpeningDate(LocalDateTime.now());
            depositEntity.setDateOfLastPercentCalculation(depositEntity.getDepositOpeningDate());
            depositEntity.setIsClosed(false);
            Optional<ConditionsEntity> conditionsEntity = conditionsService.findById(depositRequest.getConditionsId());
            depositEntity.setConditions(conditionsEntity.get());
            depositRepository.save(depositEntity);
            return mapperDeposit.depositEntityToDto(depositEntity);
        }
        return null;
    }

    public boolean closeDeposit(String accountNumber) {
        DepositEntity depositEntity = depositRepository.findByAccountNumber(accountNumber).get();
        boolean resultMakeTransver = externalServices.makeTransver(depositEntity.getClientId(),
                accountNumber, "", depositEntity.getAmmountPercent() + depositEntity.getAmmountDeposit());
        if (resultMakeTransver) {
            depositEntity.setIsClosed(true);
            depositRepository.save(depositEntity);
            return true;
        }
        return false;

    }

    public boolean withdrawDeposit(String accountNumber) {
        DepositEntity depositEntity = depositRepository.findByAccountNumber(accountNumber).get();
        boolean resultMakeTransver = externalServices.makeTransver(depositEntity.getClientId(),
                accountNumber, "", depositEntity.getAmmountPercent() + depositEntity.getAmmountDeposit());
        if (resultMakeTransver) {
            depositEntity.setIsClosed(true);
            depositRepository.save(depositEntity);
            return true;
        }
        return false;

    }

}
