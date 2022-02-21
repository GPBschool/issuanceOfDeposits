package ru.gpb.school.issuanceofdeposits.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "deposit")
public class DepositEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "client_id")
    private int clientId;   // Клиент
    @Column(name = "accoun_number")
    private String accountNumber; // номер счета депозита
    @Column(name = "ammount_deposit")
    private Double ammountDeposit; // сумма депозита
    @Column(name = "ammount_percent")
    private Double ammountPercent; // сумма процентов
    @Column(name = "deposit_opening_date")
    private LocalDateTime depositOpeningDate; // дата открытия депозита
    @Column(name = "last_date_of_percent_calculation")
    private LocalDateTime dateOfLastPercentCalculation; //дата последнего расчета процентов
    @ManyToOne
    @JoinColumn(name = "conditions_id")
    private ConditionsEntity conditions; // условия депозита
    @Column(name = "is_closed")
    private Boolean isClosed;   // закрытый
}
