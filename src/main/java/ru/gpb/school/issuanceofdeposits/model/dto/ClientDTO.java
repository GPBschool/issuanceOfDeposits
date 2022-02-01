package ru.gpb.school.issuanceofdeposits.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ClientDTO {
    private int id;
    private String firstName;   // имя
    private String lastName;    // фамилия
    private LocalDate birthdate;// дата рождения
    @JsonProperty("e-mail")
    private String email;
}
