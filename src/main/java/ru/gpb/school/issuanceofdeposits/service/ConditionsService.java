package ru.gpb.school.issuanceofdeposits.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gpb.school.issuanceofdeposits.exception.NotFoundException;
import ru.gpb.school.issuanceofdeposits.model.dto.ConditionsDto;
import ru.gpb.school.issuanceofdeposits.model.entity.ConditionsEntity;
import ru.gpb.school.issuanceofdeposits.repository.ConditionsRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConditionsService {

    private final ConditionsRepository conditionsRepository;



    public Optional<ConditionsEntity> findById(int id) throws NotFoundException {
        return conditionsRepository.findById(id);
    }

}
