package ru.gpb.school.issuanceofdeposits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gpb.school.issuanceofdeposits.model.entity.ConditionsEntity;
@Repository
public interface ConditionsRepository extends JpaRepository<ConditionsEntity, Integer> {
}
