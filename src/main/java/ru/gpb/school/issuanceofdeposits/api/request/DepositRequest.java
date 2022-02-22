package ru.gpb.school.issuanceofdeposits.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// Запрос на добавление депозита
@Data
public class DepositRequest {
    @JsonProperty("client_id")
    private int clientId;   // Клиент
    @JsonProperty("amount_deposit")
    private Double amountDeposit; // сумма депозита
    @JsonProperty("conditions_id")
    private int conditionsId; // условия депозита
}
