package com.app.repository;

import com.app.common.enums.DebtType;
import com.app.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DebtRepository extends JpaRepository<Debt, Long> {
    List<Debt> findByCitizenId(String citizenId);

    List<Debt> findByDebtType(DebtType debtType);
}
