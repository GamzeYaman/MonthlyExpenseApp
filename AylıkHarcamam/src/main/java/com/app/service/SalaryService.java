package com.app.service;

import com.app.dto.DetailOfSalaryArrangementDto;
import com.app.dto.SalarySaveDto;

import java.time.Month;

public interface SalaryService {

    String saveSalary(SalarySaveDto salarySaveDto);

    DetailOfSalaryArrangementDto showArrangementOfSalary(String citizenId, Month debtMonth, short debtYear);
}
