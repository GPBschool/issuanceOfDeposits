package ru.gpb.school.issuanceofdeposits.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepositException extends Exception{
    public DepositException(String message) {
        super(message);
    }
}
