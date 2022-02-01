package ru.gpb.school.issuanceofdeposits.repository;

import org.springframework.stereotype.Repository;
import ru.gpb.school.issuanceofdeposits.api.request.ClientRequest;
import ru.gpb.school.issuanceofdeposits.exception.NotFoundException;
import ru.gpb.school.issuanceofdeposits.model.dto.ClientDTO;

import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public class ClientRepositoryArray {

    private final ArrayList<ClientDTO> clients = new ArrayList<>();


    public ClientRepositoryArray() {
        addTestData();
    }

    public ClientDTO findById(int id) throws NotFoundException {
        return clients.stream().filter(c -> c.getId() == id).findFirst()
                .orElseThrow(() -> new NotFoundException("Client " + id + "not found"));
    }

    public ClientDTO findByNameAndBirthdateAndEmail(ClientRequest clientRequest) throws NotFoundException {

        return clients.stream().filter(c -> c.getFirstName().equals(clientRequest.getFirstName())
                && c.getLastName().equals(clientRequest.getLastName())
                && c.getBirthdate().equals(clientRequest.getBirthdate())
                && c.getEmail().equals(clientRequest.getEmail())).findFirst()
                .orElseThrow(()->new NotFoundException("Client not found"));
    }

    private void addTestData() {
        clients.add(ClientDTO.builder()
                .id(1)
                .firstName("Иван")
                .lastName("Иванов")
                .birthdate(LocalDate.parse("2009-08-25"))
                .email("ivanov@mail.ru")
                .build());
        clients.add(ClientDTO.builder()
                .id(2)
                .firstName("Александр")
                .lastName("Александров")
                .birthdate(LocalDate.parse("1986-04-30"))
                .email("alexandrov@mail.ru")
                .build());
        clients.add(ClientDTO.builder()
                .id(3)
                .firstName("Петр")
                .lastName("Петров")
                .birthdate(LocalDate.parse("1996-10-15"))
                .email("petrov@mail.ru")
                .build());
    }


}
