package ru.gpb.school.issuanceofdeposits.model.mapper;

import org.springframework.stereotype.Component;
import ru.gpb.school.issuanceofdeposits.model.dto.ConditionsDto;
import ru.gpb.school.issuanceofdeposits.model.entity.ConditionsEntity;

@Component
public class MapperConditions {

    // условие депозита из Entity в Dto
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

    // условие депозита из Dto в Entity
    public ConditionsEntity conditionsDtoToEntity(ConditionsDto conditionsRequest){
        ConditionsEntity conditions = new ConditionsEntity();
        conditions.setBlocked(conditionsRequest.getBlocked());
        conditions.setPercent(conditionsRequest.getPercent());
        conditions.setReplenishment(conditionsRequest.getReplenishment());
        conditions.setWithdrawal(conditionsRequest.getWithdrawal());
        conditions.setTermOfDeposit(conditionsRequest.getTermOfDeposit());

        return conditions;
    }
}
