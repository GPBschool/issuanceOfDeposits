package ru.gpb.school.issuanceofdeposits.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class DepositDTO {
    private int id;
    private ClientDTO client;   // Клиент
    private String accountNumber; // номер счета депозита
    private BigDecimal ammountDeposit; // сумма депозита
    private BigDecimal ammountPercent; // сумма процентов
    private LocalDateTime DepositOpeningDate; // дата открытия депозита
    private LocalDateTime dateOfLastPercentCalculation; //дата последнего расчета процентов
    private ConditionsDTO conditions; // условия депозита
}
