package ru.gpb.school.issuanceofdeposits.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gpb.school.issuanceofdeposits.model.enums.TermOfDeposit;

// Условия депозитов
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConditionsDto {
    private Integer id;
    private Double percent; // проценты
    private Boolean blocked; // блокировка условий
    private Boolean replenishment; // возможность поплнения
    private Boolean withdrawal; // возможность снятия
    @JsonProperty("term_of_deposit")
    private TermOfDeposit termOfDeposit; // срок депозита
}
