package ru.gpb.school.issuanceofdeposits.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gpb.school.issuanceofdeposits.model.dto.ConditionsDto;
import ru.gpb.school.issuanceofdeposits.model.dto.DepositDto;
import ru.gpb.school.issuanceofdeposits.model.entity.DepositEntity;

@Component
@RequiredArgsConstructor
public class MapperDeposit {

    private final MapperConditions mapperConditions;

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
                .build();
    }


}
