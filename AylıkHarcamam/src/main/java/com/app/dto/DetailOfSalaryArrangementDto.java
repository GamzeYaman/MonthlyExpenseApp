package com.app.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.HashMap;

@Data
public class DetailOfSalaryArrangementDto {
    String month;
    int year;
    BigDecimal totalDebtAmount;
    BigDecimal remainingSalary;
    HashMap<String, BigDecimal> debtListOfCitizen;
}
