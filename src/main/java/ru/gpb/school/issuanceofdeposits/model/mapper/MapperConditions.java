package ru.gpb.school.issuanceofdeposits.model.mapper;

import org.springframework.stereotype.Component;
import ru.gpb.school.issuanceofdeposits.api.request.ConditionsRequest;
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

    // условие депозита из Запроса добавления  в Entity
    public ConditionsEntity conditionsRequestToEntity(ConditionsRequest conditionsRequest){
        ConditionsEntity conditions = new ConditionsEntity();
        conditions.setBlocked(conditionsRequest.isBlocked());
        conditions.setPercent(conditionsRequest.getPercent());
        conditions.setReplenishment(conditionsRequest.isReplenishment());
        conditions.setWithdrawal(conditionsRequest.isWithdrawal());
        conditions.setTermOfDeposit(conditionsRequest.getTermOfDeposit());

        return conditions;
    }
}
