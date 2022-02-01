package ru.gpb.school.issuanceofdeposits.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super(message);
    }
}
