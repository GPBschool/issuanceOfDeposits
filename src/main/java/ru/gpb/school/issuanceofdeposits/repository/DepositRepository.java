package ru.gpb.school.issuanceofdeposits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.gpb.school.issuanceofdeposits.model.entity.DepositEntity;

import java.util.Optional;

public interface DepositRepository extends JpaRepository<DepositEntity, Integer> {

    Optional<DepositEntity> findByAccountNumber(String accountNumber);


}
