package ru.gpb.school.issuanceofdeposits.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gpb.school.issuanceofdeposits.exception.NotFoundException;
import ru.gpb.school.issuanceofdeposits.model.dto.ClientDTO;
import ru.gpb.school.issuanceofdeposits.model.dto.ConditionsDTO;
import ru.gpb.school.issuanceofdeposits.repository.ClientRepositoryArray;
import ru.gpb.school.issuanceofdeposits.repository.ConditionsRepositoryArray;

@Service
@RequiredArgsConstructor
public class ConditionsService {

    private final ConditionsRepositoryArray conditionsRepository;



    public ConditionsDTO findById(int id) throws NotFoundException {
        return conditionsRepository.findById(id);
    }

}
