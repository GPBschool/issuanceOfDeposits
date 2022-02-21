package ru.gpb.school.issuanceofdeposits.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gpb.school.issuanceofdeposits.api.request.AccountRequest;
import ru.gpb.school.issuanceofdeposits.api.request.ClientRequest;
import ru.gpb.school.issuanceofdeposits.api.request.DepositRequest;
import ru.gpb.school.issuanceofdeposits.api.respons.DepositRespons;
import ru.gpb.school.issuanceofdeposits.exception.NotFoundException;
import ru.gpb.school.issuanceofdeposits.model.dto.DepositDto;
import ru.gpb.school.issuanceofdeposits.service.ClientService;
import ru.gpb.school.issuanceofdeposits.service.DepositService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class DepositContoller {

    private final DepositService depositService;
    private final ClientService clientService;

    @GetMapping("/list")
    public ResponseEntity<Collection<DepositDto>> getDepositList(@RequestBody ClientRequest client) {
//        ClientDTO clientDTO;
//        try {
//            clientDTO = clientService.findByNameAndBirthdateAndEmail(client);
//        } catch (NotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(depositService.findByClientId(clientDTO.getId()));
        return null;
    }


    // информация по всем депозитам клиента
    @GetMapping("/depositinfobyclient")
    public ResponseEntity<DepositDto> getDepositInfoByClient(@RequestParam(value = "deposit", defaultValue = "0") int depositId) {
//        DepositDTO depositDTO;
//        try {
//            depositDTO = depositService.findById(depositId);
//        } catch (NotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(depositDTO);
        return null;
    }

    // пополнить депозит
    @PostMapping("/depositfounds")
    public ResponseEntity<?> depositFounds(@RequestBody DepositRequest depositRequest) {
//        TODO реализовать пополнение депозита
        return ResponseEntity.ok(new DepositDto());
    }

    // закрыть депозит
    @PostMapping("/closedeposit")
    public ResponseEntity<?> closeDeposit(@RequestBody AccountRequest accountRequest) {

        if (depositService.closeDeposit(accountRequest.getAccountNumber()))
            ResponseEntity.ok("close deposit");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    // вывести на счет проценты по депозиту
    @PostMapping("/withdrawdeposit")
    public ResponseEntity<?> withdrawDeposit(@RequestBody AccountRequest accountRequest) {
        // TODO реализовать тело снятия процентов

        return ResponseEntity.ok(new DepositDto());
    }

    // Открытие депозита
    @PostMapping("/opendeposit")
    public ResponseEntity<?> openDeposit(@RequestBody DepositRequest depositRequest) {
        DepositDto depositDto = null;
        try {
            depositDto = depositService.addDeposit(depositRequest);
            return ResponseEntity.ok(depositDto);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
