package ru.gpb.school.issuanceofdeposits.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.gpb.school.issuanceofdeposits.model.enums.TermOfDeposit;

import javax.persistence.*;

// Условия депозитов
@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "conditions")
public class ConditionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double percent; // проценты
    private boolean blocked; // блокировка условий
    private boolean replenishment; // возможность поплнения
    private boolean withdrawal; // возможность снятия
    @Enumerated(EnumType.STRING)
    @Column(name = "term_of_deposit")
    private TermOfDeposit termOfDeposit; // срок депозита
}
