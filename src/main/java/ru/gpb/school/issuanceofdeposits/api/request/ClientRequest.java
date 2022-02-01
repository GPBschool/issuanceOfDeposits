package ru.gpb.school.issuanceofdeposits.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientRequest {
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    @JsonProperty("e-mail")
    private String email;
}
