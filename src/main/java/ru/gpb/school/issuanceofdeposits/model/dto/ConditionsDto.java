package ru.gpb.school.issuanceofdeposits.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import ru.gpb.school.issuanceofdeposits.model.enums.TermOfDeposit;

// Условия депозитов
@Data
@Builder
public class ConditionsDto {
    private int id;
    private double percent; // проценты
    private boolean blocked; // блокировка условий
    private boolean replenishment; // возможность поплнения
    private boolean withdrawal; // возможность снятия
    @JsonProperty("term_of_deposit")
    private TermOfDeposit termOfDeposit; // срок депозита
}
