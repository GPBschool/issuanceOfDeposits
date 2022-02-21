package ru.gpb.school.issuanceofdeposits.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gpb.school.issuanceofdeposits.model.enums.TermOfDeposit;

// Запрос на добавление Условий депозитов
@Data
@NoArgsConstructor
public class ConditionsRequest {
    private double percent; // проценты
    private boolean blocked; // блокировка условий
    private boolean replenishment; // возможность поплнения
    private boolean withdrawal; // возможность снятия
    @JsonProperty("term_of_deposit")
    private TermOfDeposit termOfDeposit; // срок депозита
}
