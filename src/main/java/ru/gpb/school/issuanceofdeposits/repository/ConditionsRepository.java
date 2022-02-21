package ru.gpb.school.issuanceofdeposits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gpb.school.issuanceofdeposits.model.entity.ConditionsEntity;

public interface ConditionsRepository extends JpaRepository<ConditionsEntity, Integer> {
}
