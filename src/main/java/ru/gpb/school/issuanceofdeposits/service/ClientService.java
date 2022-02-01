package ru.gpb.school.issuanceofdeposits.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gpb.school.issuanceofdeposits.api.request.ClientRequest;
import ru.gpb.school.issuanceofdeposits.exception.NotFoundException;
import ru.gpb.school.issuanceofdeposits.model.dto.ClientDTO;
import ru.gpb.school.issuanceofdeposits.repository.ClientRepositoryArray;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepositoryArray clientRepository;



    public ClientDTO findById(int id) throws NotFoundException {
        return clientRepository.findById(id);
    }

    public ClientDTO findByNameAndBirthdateAndEmail(ClientRequest clientRequest) throws NotFoundException {
        return clientRepository.findByNameAndBirthdateAndEmail(clientRequest);

    }
}
