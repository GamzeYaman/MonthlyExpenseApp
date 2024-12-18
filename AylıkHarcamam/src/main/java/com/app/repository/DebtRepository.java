package com.app.repository;

import com.app.dto.DebtSearchDto;
import com.app.dto.MonthlyDebt;
import com.app.entity.Debt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Month;
import java.util.List;

public interface DebtRepository extends JpaRepository<Debt, Long> {
    List<Debt> findByCitizenId(String citizenId);

    @Query("""
        select debt from Debt debt
        where
         (:#{#debtSearchDto.debtType} is null or debt.debtType = :#{#debtSearchDto.debtType}) and
         (:#{#debtSearchDto.debtMonth} is null or debt.debtMonth = :#{#debtSearchDto.debtMonth}) and
         (:#{#debtSearchDto.debtYear} is null or debt.debtYear = :#{#debtSearchDto.debtYear})
    """)
    Page<Debt> searchDebtByCriterias(DebtSearchDto debtSearchDto, Pageable pageable);

    List<MonthlyDebt> findByCitizenIdAndDebtMonthAndDebtYear(String citizenId, Month debtMonth, short debtYear);
}

