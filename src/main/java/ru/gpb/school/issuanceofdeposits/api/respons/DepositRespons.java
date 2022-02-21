package ru.gpb.school.issuanceofdeposits.api.respons;

import lombok.Data;
import ru.gpb.school.issuanceofdeposits.model.dto.DepositDto;

@Data
public class DepositRespons {
    private boolean result;
    private DepositDto depositDTO;
}
