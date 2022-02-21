package ru.gpb.school.issuanceofdeposits.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// запрос по клиенту и номеру счета
@Data
public class AccountRequest {
    @JsonProperty("client_id")
    private int clientId;
    @JsonProperty("account_number")
    private String accountNumber;
}
