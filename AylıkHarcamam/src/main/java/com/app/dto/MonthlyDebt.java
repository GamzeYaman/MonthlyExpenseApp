package com.app.dto;

import com.app.common.enums.DebtType;

import java.math.BigDecimal;
import java.time.Month;

public record MonthlyDebt(Long id, DebtType debtType, BigDecimal debtAmount, Month debtMonth, short debtYear) {

}
