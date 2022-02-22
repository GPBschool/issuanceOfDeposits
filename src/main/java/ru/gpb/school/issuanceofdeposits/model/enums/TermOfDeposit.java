package ru.gpb.school.issuanceofdeposits.model.enums;

import lombok.Getter;

// Срок депозита
@Getter
public enum TermOfDeposit {
    ONE_DAY(60),
    ONE_WEEK(120),
    ONE_MONTH(180),
    SIX_MONTHS(240),
    ONE_YEAR(300),
    THREE_YEARS(360),
    UNLIMITED(0);

    private int seconds;

    TermOfDeposit(int seconds) {
        this.seconds = seconds;
    }
}
