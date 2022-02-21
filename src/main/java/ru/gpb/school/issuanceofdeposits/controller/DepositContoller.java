package ru.gpb.school.issuanceofdeposits.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gpb.school.issuanceofdeposits.api.request.AccountRequest;
import ru.gpb.school.issuanceofdeposits.api.request.DepositFoundsRequest;
import ru.gpb.school.issuanceofdeposits.api.request.DepositRequest;
import ru.gpb.school.issuanceofdeposits.exception.DepositException;
import ru.gpb.school.issuanceofdeposits.exception.NotFoundException;
import ru.gpb.school.issuanceofdeposits.model.dto.DepositDto;
import ru.gpb.school.issuanceofdeposits.service.DepositService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class DepositContoller {

    private final DepositService depositService;

    // информация по всем депозитам клиента
    @GetMapping("/depositinfobyclient/{clientId}")
    public ResponseEntity<Collection<DepositDto>> getDepositInfoByClient(@PathVariable Integer clientId) {
        return ResponseEntity.ok(depositService.findDepositByClientId(clientId));

    }

    // пополнить депозит
    @PostMapping("/depositfounds")
    public ResponseEntity<?> depositFounds(@RequestBody DepositFoundsRequest depositFoundsRequest)
            throws DepositException, NotFoundException {
        depositService.depositFounds(depositFoundsRequest);
        return ResponseEntity.ok("deposit founds");
    }

    // закрыть депозит
    @PostMapping("/closedeposit")
    public ResponseEntity<?> closeDeposit(@RequestBody AccountRequest accountRequest)
            throws NotFoundException, DepositException {
        depositService.closeDeposit(accountRequest.getAccountNumber());
        return ResponseEntity.ok("close deposit");
    }

    // вывести на счет проценты по депозиту
    @PostMapping("/withdrawdeposit")
    public ResponseEntity<?> withdrawDeposit(@RequestBody AccountRequest accountRequest)
            throws NotFoundException, DepositException {
        depositService.withdrawDeposit(accountRequest.getAccountNumber());
        return ResponseEntity.ok("interest transferred");
    }

    // Открытие депозита
    @PostMapping("/opendeposit")
    public ResponseEntity<?> openDeposit(@RequestBody DepositRequest depositRequest)
            throws NotFoundException, DepositException {
        DepositDto depositDto = depositService.addDeposit(depositRequest);
        return ResponseEntity.ok(depositDto);
    }

}
