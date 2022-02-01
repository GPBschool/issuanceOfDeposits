package ru.gpb.school.issuanceofdeposits.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gpb.school.issuanceofdeposits.api.request.ClientRequest;
import ru.gpb.school.issuanceofdeposits.api.request.DepositRequest;
import ru.gpb.school.issuanceofdeposits.api.respons.DepositRespons;
import ru.gpb.school.issuanceofdeposits.exception.NotFoundException;
import ru.gpb.school.issuanceofdeposits.model.dto.ClientDTO;
import ru.gpb.school.issuanceofdeposits.model.dto.DepositDTO;
import ru.gpb.school.issuanceofdeposits.service.ClientService;
import ru.gpb.school.issuanceofdeposits.service.DepositService;

import java.util.Collection;

@RestController
@RequestMapping("/deposit")
@RequiredArgsConstructor
public class DepositContoller {

    private final DepositService depositService;
    private final ClientService clientService;

    @GetMapping("/list")
    public ResponseEntity<Collection<DepositDTO>> getDepositList(@RequestBody ClientRequest client) {
        ClientDTO clientDTO;
        try {
            clientDTO = clientService.findByNameAndBirthdateAndEmail(client);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(depositService.findByClientId(clientDTO.getId()));
    }

    @GetMapping("/detail")
    public ResponseEntity<DepositDTO> getDepositDetail(@RequestParam(value = "deposit", defaultValue = "0") int depositId) {
        DepositDTO depositDTO;
        try {
            depositDTO = depositService.findById(depositId);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(depositDTO);
    }

    @PostMapping()
    public ResponseEntity<DepositRespons> addDeposit(@RequestBody DepositRequest depositRequest) {
        DepositRespons depositRespons = new DepositRespons();

        try {
            depositRespons.setDepositDTO( depositService.addDeposit(depositRequest));
            depositRespons.setResult(true);
        } catch (NotFoundException e) {
            depositRespons.setResult(false);
        }

        return ResponseEntity.ok(depositRespons);
    }

}
