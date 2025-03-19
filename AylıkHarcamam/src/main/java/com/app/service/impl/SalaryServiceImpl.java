package com.app.service.impl;

import com.app.common.Parameter;
import com.app.common.exception.exceptionmessage.GeneralExceptionMessages;
import com.app.common.exception.exceptions.AlreadyHaveException;
import com.app.common.exception.exceptions.InvalidDataException;
import com.app.common.exception.exceptions.ItemNotFoundException;
import com.app.common.utils.DebtUtils;
import com.app.common.validation.ValidationUtils;
import com.app.converter.SalaryMapper;
import com.app.dto.DetailOfSalaryArrangementDto;
import com.app.dto.MonthlyDebt;
import com.app.dto.SalarySaveDto;
import com.app.entity.Salary;
import com.app.repository.DebtRepository;
import com.app.repository.SalaryRepository;
import com.app.service.DebtService;
import com.app.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository salaryRepository;
    private final DebtRepository debtRepository;

    @Override
    public String saveSalary(SalarySaveDto salarySaveDto) {
        isDtoValuesNull(salarySaveDto);
        isThereSalaryInMonthAndYear(Month.of(salarySaveDto.getSalaryMonth()), salarySaveDto.getSalaryYear(), salarySaveDto.getCitizenId());
        Salary salary = SalaryMapper.INSTANCE.convertToSalaryFromSalarySaveDto(salarySaveDto);
        salary = salaryRepository.save(salary);
        return getSavedSalaryInfo(salary);
    }

    @Override
    public String updateSalary(Long salaryId, SalarySaveDto salaryUpdateDto) {
        isDtoValuesNull(salaryUpdateDto);
        isThereSalaryInMonthAndYear(Month.of(salaryUpdateDto.getSalaryMonth()), salaryUpdateDto.getSalaryYear(), salaryUpdateDto.getCitizenId());
        Salary salary = salaryRepository.findById(salaryId).orElseThrow(() -> new ItemNotFoundException(GeneralExceptionMessages.SALARY_NOT_FOUND.getMessage()));
        SalaryMapper.INSTANCE.updateSalaryFromDto(salaryUpdateDto, salary);
        salary = salaryRepository.save(salary);
        return getSavedSalaryInfo(salary);
    }

    @Override
    public DetailOfSalaryArrangementDto showArrangementOfSalary(String citizenId, Month debtMonth, short debtYear) {
        List<MonthlyDebt> monthlyDebtList = debtRepository.findByCitizenIdAndDebtMonthAndDebtYear(citizenId, debtMonth, debtYear);

        Salary salary = salaryRepository.findByCitizenId(citizenId);

        BigDecimal totalDebtAmount = DebtUtils.calculateTotalDebtAmount(monthlyDebtList);

        BigDecimal remainingSalary = DebtUtils.calculateRemainingMoneyInSalary(salary, totalDebtAmount);

        return SalaryMapper.INSTANCE.convertToDetailOfSalaryArrangementDto(salary, totalDebtAmount, remainingSalary, DebtUtils.getDebtNameAndAmountList(monthlyDebtList));
    }

    private String getSavedSalaryInfo(Salary salary) {
        return Parameter.CURRENCY_FORMATTER.format(salary.getSalaryAmount()) + "/"
                + salary.getSalaryYear() + "-"
                + salary.getSalaryMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("tr"));
    }

    private void isDtoValuesNull(SalarySaveDto salarySaveDto){
        if(ValidationUtils.isValueNull(salarySaveDto.getSalaryAmount())) {
            throw new InvalidDataException("Maaş miktarı bilgisi " + GeneralExceptionMessages.EMPTY_DATA.getMessage());
        } else if(ValidationUtils.isValueNull(salarySaveDto.getSalaryYear()) || salarySaveDto.getSalaryYear() == 0) {
            throw new InvalidDataException("Maaş yılı bilgisi " + GeneralExceptionMessages.EMPTY_DATA.getMessage());
        } else if(ValidationUtils.isValueNull(salarySaveDto.getSalaryMonth()) || salarySaveDto.getSalaryMonth() == 0) {
            throw new InvalidDataException("Maaş ayı bilgisi " + GeneralExceptionMessages.EMPTY_DATA.getMessage());
        }
    }

    private void isThereSalaryInMonthAndYear(Month salaryMonth, int salaryYear, String citizenId) {
        if(salaryRepository.existsBySalaryMonthAndSalaryYearAndCitizen_Id(salaryMonth, salaryYear, citizenId)) {
            throw new AlreadyHaveException(salaryMonth.getDisplayName(TextStyle.FULL, new Locale("tr", "TR"))
                    + "-" + salaryYear + GeneralExceptionMessages.SALARY_ALREADY_USED.getMessage());
        }
    }
}
