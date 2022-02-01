package ru.gpb.school.issuanceofdeposits.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequest {
    @JsonProperty("client_id")
    private int clientId;   // Клиент
    private BigDecimal ammountDeposit; // сумма депозита
    @JsonProperty("conditions_id")
    private int conditionsId; // условия депозита
}
