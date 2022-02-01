package ru.gpb.school.issuanceofdeposits.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gpb.school.issuanceofdeposits.api.request.DepositRequest;
import ru.gpb.school.issuanceofdeposits.exception.NotFoundException;
import ru.gpb.school.issuanceofdeposits.model.dto.DepositDTO;
import ru.gpb.school.issuanceofdeposits.repository.DepositRepositoryArray;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DepositService {
    private final DepositRepositoryArray depositRepository;


    public Collection<DepositDTO> findByClientId(int clientId) {
        return depositRepository.findByClientId(clientId);
    }

    public DepositDTO findById(int id) throws NotFoundException {
        return depositRepository.findById(id);
    }

    public DepositDTO addDeposit(DepositRequest depositRequest) throws NotFoundException {

        return depositRepository.addDeposit(depositRequest);
    }

}
