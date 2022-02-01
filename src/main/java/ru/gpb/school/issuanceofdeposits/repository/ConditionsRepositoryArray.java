package ru.gpb.school.issuanceofdeposits.repository;

import org.springframework.stereotype.Repository;
import ru.gpb.school.issuanceofdeposits.exception.NotFoundException;
import ru.gpb.school.issuanceofdeposits.model.dto.ConditionsDTO;
import ru.gpb.school.issuanceofdeposits.model.enums.TermsOfDeposits;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public class ConditionsRepositoryArray {

    private final ArrayList<ConditionsDTO> conditions = new ArrayList<>();


    public ConditionsRepositoryArray() {
        addTestData();
    }

    public ConditionsDTO findById(int id) throws NotFoundException {
        return conditions.stream().filter(c -> c.getId()==id).findFirst()
                .orElseThrow(()-> new NotFoundException("Conditions "+id+"not found"));
    }

    public Collection<ConditionsDTO> findAll(){
        return conditions;
    }

    private void addTestData() {
        conditions.add(ConditionsDTO.builder()
                .id(1)
                .percent(15)
                .blocked(false)
                .replenishment(false)
                .withdrawal(false)
                .termsOfDeposits(TermsOfDeposits.ONE_DAY)
                .build());
        conditions.add(ConditionsDTO.builder()
                .id(2)
                .percent(5)
                .blocked(false)
                .replenishment(true)
                .withdrawal(true)
                .termsOfDeposits(TermsOfDeposits.ONE_DAY)
                .build());
        conditions.add(ConditionsDTO.builder()
                .id(3)
                .percent(8)
                .blocked(false)
                .replenishment(false)
                .withdrawal(false)
                .termsOfDeposits(TermsOfDeposits.ONE_DAY)
                .build());
    }
}
