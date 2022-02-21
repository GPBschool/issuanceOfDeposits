package ru.gpb.school.issuanceofdeposits.model.mapper;

import ru.gpb.school.issuanceofdeposits.model.dto.ConditionsDto;
import ru.gpb.school.issuanceofdeposits.model.entity.ConditionsEntity;

public class MapperConditions {

    public ConditionsDto conditionsEntityToDto(ConditionsEntity conditionsEntity){
        return ConditionsDto.builder()
                .id(conditionsEntity.getId())
                .blocked(conditionsEntity.isBlocked())
                .percent(conditionsEntity.getPercent())
                .replenishment(conditionsEntity.isReplenishment())
                .termOfDeposit(conditionsEntity.getTermOfDeposit())
                .withdrawal(conditionsEntity.isWithdrawal())
                .build();
    }
}
