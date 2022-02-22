package ru.gpb.school.issuanceofdeposits.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gpb.school.issuanceofdeposits.api.request.ConditionsRequest;
import ru.gpb.school.issuanceofdeposits.exception.NotFoundException;
import ru.gpb.school.issuanceofdeposits.model.dto.ConditionsDto;
import ru.gpb.school.issuanceofdeposits.model.entity.ConditionsEntity;
import ru.gpb.school.issuanceofdeposits.model.mapper.MapperConditions;
import ru.gpb.school.issuanceofdeposits.repository.ConditionsRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConditionsService {

    private final ConditionsRepository conditionsRepository;
    private final MapperConditions mapperConditions;

    // получить условие по id и вернуть Entity
    public ConditionsEntity findById(int id) throws NotFoundException {
        return conditionsRepository.findById(id).orElseThrow(()->new NotFoundException("Not found conditions by id "+id));
    }

    // получить условие по id и вернуть Dto
    public ConditionsDto getById(int id) throws NotFoundException {
        return mapperConditions.conditionsEntityToDto(findById(id));
    }

    // получить все условия депозитов
    public Collection<ConditionsDto> findAll() {
        Collection<ConditionsEntity> conditionsEntity = conditionsRepository.findAll();
        return conditionsEntity.stream().map(mapperConditions::conditionsEntityToDto).toList();
    }

    // добавить условие для депозитов
    public ConditionsDto addCondition(ConditionsRequest conditionsRequest){
        ConditionsEntity conditions =  conditionsRepository.save(mapperConditions.conditionsRequestToEntity(conditionsRequest));
        return mapperConditions.conditionsEntityToDto(conditions);
    }
}
