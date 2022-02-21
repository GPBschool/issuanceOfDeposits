package ru.gpb.school.issuanceofdeposits.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gpb.school.issuanceofdeposits.api.request.DepositRequest;
import ru.gpb.school.issuanceofdeposits.model.dto.ConditionsDto;
import ru.gpb.school.issuanceofdeposits.model.dto.DepositDto;
import ru.gpb.school.issuanceofdeposits.model.entity.ConditionsEntity;
import ru.gpb.school.issuanceofdeposits.model.entity.DepositEntity;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MapperDeposit {

    private final MapperConditions mapperConditions;

    // Депозит из Entity в Dto
    public DepositDto depositEntityToDto(DepositEntity depositEntity){
        return DepositDto.builder()
                .id(depositEntity.getId())
                .ammountDeposit(depositEntity.getAmmountDeposit())
                .accountNumber(depositEntity.getAccountNumber())
                .ammountPercent(depositEntity.getAmmountPercent())
                .DepositOpeningDate(depositEntity.getDepositOpeningDate())
                .clientId(depositEntity.getClientId())
                .conditions(mapperConditions.conditionsEntityToDto(depositEntity.getConditions()))
                .dateOfLastPercentCalculation(depositEntity.getDateOfLastPercentCalculation())
                .isClosed(depositEntity.getIsClosed())
                .ammountPercent(depositEntity.getAmmountPercent())
                .build();
    }

    // Депозит из запроса добавления в Entity
    public DepositEntity depositRequestToEntity(DepositRequest depositRequest,
                                                String accountNumber,ConditionsEntity conditionsEntity){
        DepositEntity deposit = new DepositEntity();
        deposit.setClientId(depositRequest.getClientId());
        deposit.setAccountNumber(accountNumber);
        deposit.setAmmountDeposit(depositRequest.getAmmountDeposit());
        deposit.setDepositOpeningDate(LocalDateTime.now());
        deposit.setDateOfLastPercentCalculation(deposit.getDepositOpeningDate());
        deposit.setIsClosed(false);
        deposit.setConditions(conditionsEntity);
        deposit.setAmmountPercent(0.);
        return deposit;
    }

}
