package ru.gpb.school.issuanceofdeposits.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gpb.school.issuanceofdeposits.api.request.ClientRequest;
import ru.gpb.school.issuanceofdeposits.exception.NotFoundException;
import ru.gpb.school.issuanceofdeposits.model.dto.ClientDto;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepositoryArray clientRepository;



    public ClientDto findById(int id) throws NotFoundException {
        return clientRepository.findById(id);
    }

    public ClientDto findByNameAndBirthdateAndEmail(ClientRequest clientRequest) throws NotFoundException {
        return clientRepository.findByNameAndBirthdateAndEmail(clientRequest);

    }
}
