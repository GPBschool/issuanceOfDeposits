package ru.gpb.school.issuanceofdeposits.api.respons;

import lombok.Data;
import ru.gpb.school.issuanceofdeposits.model.dto.DepositDTO;

@Data
public class DepositRespons {
    private boolean result;
    private DepositDTO depositDTO;
}
