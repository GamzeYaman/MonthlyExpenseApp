package com.app.common.utils;

import com.app.dto.MonthlyDebt;
import com.app.entity.Salary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DebtUtils {
    public static HashMap<String, BigDecimal> getDebtNameAndAmountList(List<MonthlyDebt> monthlyDebtList) {
        HashMap<String, BigDecimal> debtNameAndAmountList = (HashMap<String, BigDecimal>) monthlyDebtList.stream()
                .collect(Collectors.toMap(monthlyDebt -> monthlyDebt.debtType().getText(), MonthlyDebt::debtAmount));

        return debtNameAndAmountList;
    }

    public static BigDecimal calculateTotalDebtAmount(List<MonthlyDebt> monthlyDebtList) {
        return BigDecimal.valueOf(monthlyDebtList.stream().mapToDouble(debt -> debt.debtAmount().doubleValue()).sum());
    }

    public static BigDecimal calculateRemainingMoneyInSalary(Salary salary, BigDecimal totalDebtAmount) {
        return salary.getSalaryAmount().subtract(totalDebtAmount);
    }

}
