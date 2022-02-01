package ru.gpb.school.issuanceofdeposits.repository;

import org.springframework.stereotype.Repository;
import ru.gpb.school.issuanceofdeposits.api.request.DepositRequest;
import ru.gpb.school.issuanceofdeposits.exception.NotFoundException;
import ru.gpb.school.issuanceofdeposits.model.dto.ClientDTO;
import ru.gpb.school.issuanceofdeposits.model.dto.ConditionsDTO;
import ru.gpb.school.issuanceofdeposits.model.dto.DepositDTO;
import ru.gpb.school.issuanceofdeposits.service.ClientService;
import ru.gpb.school.issuanceofdeposits.service.ConditionsService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public class DepositRepositoryArray {

    private final ArrayList<DepositDTO> deposits = new ArrayList<>();
    private static int idGen = 5;

    private final ClientService clientService;
    private final ConditionsService conditionsService;

    public DepositRepositoryArray(ClientService clientService,
                                  ConditionsService conditionsService) {
        this.clientService = clientService;
        this.conditionsService = conditionsService;

        try {
            addTestData();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

    }

    public DepositDTO findById(int id) throws NotFoundException {
        return deposits.stream().filter(c -> c.getId() == id).findFirst()
                .orElseThrow(() -> new NotFoundException("Deposit " + id + "not found"));
    }

    public DepositDTO addDeposit(DepositRequest depositRequest) throws NotFoundException {
        ClientDTO client;
        ConditionsDTO conditions;
        //try {
            client = clientService.findById(depositRequest.getClientId());
            conditions = conditionsService.findById(depositRequest.getConditionsId());
        //} catch (NotFoundException e) {
        //    new NotFoundException(e.getMessage());
       // }

        DepositDTO depositDTO = DepositDTO.builder()
                .id(++idGen)
                .accountNumber(generateNumberDeposit())
                .ammountDeposit(depositRequest.getAmmountDeposit())
                .client(client)
                .conditions(conditions)
                .DepositOpeningDate(LocalDateTime.now())
                .build();
        deposits.add(depositDTO);
        return depositDTO;
    }

    private String generateNumberDeposit(){
        return "00000"+idGen;
    }

    public Collection<DepositDTO> findByClientId(int clientId) {
        return deposits.stream().filter(deposit -> deposit.getClient().getId() == clientId).collect(Collectors.toList());
    }

    public Collection<DepositDTO> findAll() {
        return deposits;
    }

    private void addTestData() throws NotFoundException {
        deposits.add(DepositDTO.builder()
                .id(1)
                .accountNumber("1111111111")
                .ammountDeposit(BigDecimal.valueOf(5000))
                .DepositOpeningDate(LocalDateTime.now())
                .ammountPercent(BigDecimal.valueOf(0))
                .conditions(conditionsService.findById(1))
                .client(clientService.findById(1))
                .build());
        deposits.add(DepositDTO.builder()
                .id(2)
                .accountNumber("2222222222")
                .ammountDeposit(BigDecimal.valueOf(5000))
                .ammountPercent(BigDecimal.valueOf(0))
                .DepositOpeningDate(LocalDateTime.now())
                .conditions(conditionsService.findById(1))
                .client(clientService.findById(2))
                .build());
        deposits.add(DepositDTO.builder()
                .id(3)
                .accountNumber("3333333333")
                .ammountDeposit(BigDecimal.valueOf(5000))
                .ammountPercent(BigDecimal.valueOf(0))
                .DepositOpeningDate(LocalDateTime.now())
                .conditions(conditionsService.findById(2))
                .client(clientService.findById(1))
                .build());
    }


}
