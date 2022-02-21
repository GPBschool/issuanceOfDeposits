package ru.gpb.school.issuanceofdeposits.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// Запрос на добавление суммы на депозит
@Data
public class DepositFoundsRequest {
    @JsonProperty("client_id")
    private int clientId;   // Клиент
    @JsonProperty("ammount_deposit")
    private Double ammountDeposit; // сумма пополнения депозита
    @JsonProperty("account_number")
    private String accountNumber; // номер счета депозита
}
