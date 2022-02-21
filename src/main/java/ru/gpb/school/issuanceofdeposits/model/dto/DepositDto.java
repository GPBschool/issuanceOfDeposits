package ru.gpb.school.issuanceofdeposits.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// депозиты
@Data
@Builder
public class DepositDto {
    private int id;
    private int clientId;   // Клиент
    private String accountNumber; // номер счета депозита
    private Double ammountDeposit; // сумма депозита
    private Double ammountPercent; // сумма процентов
    private LocalDateTime DepositOpeningDate; // дата открытия депозита
    private LocalDateTime dateOfLastPercentCalculation; //дата последнего расчета процентов
    private ConditionsDto conditions; // условия депозита
    private Boolean isClosed;   // закрытый
}
