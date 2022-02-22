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
    public ResponseEntity<DepositDto> depositFounds(@RequestBody DepositFoundsRequest depositFoundsRequest)
            throws DepositException, NotFoundException {
        return ResponseEntity.ok(depositService.depositFounds(depositFoundsRequest));
    }

    // закрыть депозит
    @PostMapping("/closedeposit")
    public ResponseEntity<DepositDto> closeDeposit(@RequestBody AccountRequest accountRequest)
            throws NotFoundException, DepositException {
        return ResponseEntity.ok(depositService.closeDeposit(accountRequest.getAccountNumber()));
    }

    // вывести на счет проценты по депозиту
    @PostMapping("/withdrawdeposit")
    public ResponseEntity<DepositDto> withdrawDeposit(@RequestBody AccountRequest accountRequest)
            throws NotFoundException, DepositException {
        return ResponseEntity.ok(depositService.withdrawDeposit(accountRequest.getAccountNumber()));
    }

    // Открытие депозита
    @PostMapping("/opendeposit")
    public ResponseEntity<DepositDto> openDeposit(@RequestBody DepositRequest depositRequest)
            throws NotFoundException, DepositException {
        return ResponseEntity.ok(depositService.addDeposit(depositRequest));
    }

}
