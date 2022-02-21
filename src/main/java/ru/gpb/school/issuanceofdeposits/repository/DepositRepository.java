package ru.gpb.school.issuanceofdeposits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gpb.school.issuanceofdeposits.model.entity.DepositEntity;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface DepositRepository extends JpaRepository<DepositEntity, Integer> {

    Optional<DepositEntity> findByAccountNumber(String accountNumber);

    Collection<DepositEntity> findDepositEntityByClientIdAndIsClosedFalse(Integer clientId);

}
