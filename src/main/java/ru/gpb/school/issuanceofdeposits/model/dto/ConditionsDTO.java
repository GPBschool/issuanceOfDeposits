package ru.gpb.school.issuanceofdeposits.model.dto;

import lombok.Builder;
import lombok.Data;
import ru.gpb.school.issuanceofdeposits.model.enums.TermsOfDeposits;

// Условия депозитов
@Data
@Builder
public class ConditionsDTO {
    private int id;
    private double percent; // проценты
    private boolean blocked; // блокировка условий
    private boolean replenishment; // возможность поплнения
    private boolean withdrawal; // возможность снятия
    private TermsOfDeposits termsOfDeposits; // период вклада депозита
}
