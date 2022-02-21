package ru.gpb.school.issuanceofdeposits.model.dto;

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
    private TermOfDeposit termOfDeposit; // срок депозита
}
