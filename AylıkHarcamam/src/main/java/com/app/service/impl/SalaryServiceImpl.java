package com.app.service.impl;

import com.app.common.Parameter;
import com.app.common.validation.ValidationUtils;
import com.app.converter.SalaryMapper;
import com.app.dto.DetailOfSalaryArrangementDto;
import com.app.dto.MonthlyDebt;
import com.app.dto.SalarySaveDto;
import com.app.entity.Salary;
import com.app.repository.DebtRepository;
import com.app.repository.SalaryRepository;
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
        isThereSalaryInMonthAndYear(Month.of(salarySaveDto.getSalaryMonth()), salarySaveDto.getSalaryYear());
        Salary salary = SalaryMapper.INSTANCE.convertToSalaryFromSalarySaveDto(salarySaveDto);
        salary = salaryRepository.save(salary);
        return getSavedSalaryInfo(salary);
    }

    @Override
    public String updateSalary(Long salaryId, SalarySaveDto salaryUpdateDto) {
        isDtoValuesNull(salaryUpdateDto);
        isThereSalaryInMonthAndYear(Month.of(salaryUpdateDto.getSalaryMonth()), salaryUpdateDto.getSalaryYear());
        Salary salary = salaryRepository.findById(salaryId).orElseThrow(() -> new RuntimeException("İlgili maaş bilgisi bulunamadı!"));
        SalaryMapper.INSTANCE.updateSalaryFromDto(salaryUpdateDto, salary);
        salary = salaryRepository.save(salary);
        return getSavedSalaryInfo(salary);
    }

    @Override
    public DetailOfSalaryArrangementDto showArrangementOfSalary(String citizenId, Month debtMonth, short debtYear) {
        List<MonthlyDebt> monthlyDebtList = debtRepository.findByCitizenIdAndDebtMonthAndDebtYear(citizenId, debtMonth, debtYear);

        Salary salary = salaryRepository.findByCitizenId(citizenId);

        BigDecimal totalDebtAmount = calculateTotalDebtAmount(monthlyDebtList);

        BigDecimal remainingSalary = calculateRemainingMoneyInSalary(salary, totalDebtAmount);

        return SalaryMapper.INSTANCE.convertToDetailOfSalaryArrangementDto(salary, totalDebtAmount, remainingSalary, getDebtNameAndAmountList(monthlyDebtList));
    }

    private String getSavedSalaryInfo(Salary salary) {
        return Parameter.CURRENCY_FORMATTER.format(salary.getSalaryAmount()) + "/"
                + salary.getSalaryYear() + "-"
                + salary.getSalaryMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("tr"));
    }

    private BigDecimal calculateTotalDebtAmount(List<MonthlyDebt> monthlyDebtList) {
        return BigDecimal.valueOf(monthlyDebtList.stream().mapToDouble(debt -> debt.debtAmount().doubleValue()).sum());
    }

    private BigDecimal calculateRemainingMoneyInSalary(Salary salary, BigDecimal totalDebtAmount) {
        return salary.getSalaryAmount().subtract(totalDebtAmount);
    }

    private HashMap<String, BigDecimal> getDebtNameAndAmountList(List<MonthlyDebt> monthlyDebtList) {
        HashMap<String, BigDecimal> debtNameAndAmountList = (HashMap<String, BigDecimal>) monthlyDebtList.stream()
                .collect(Collectors.toMap(monthlyDebt -> monthlyDebt.debtType().getText(), MonthlyDebt::debtAmount));

        return debtNameAndAmountList;
    }

    private void isDtoValuesNull(SalarySaveDto salarySaveDto){
        if(ValidationUtils.isValueNull(salarySaveDto.getSalaryAmount())) {
            throw new RuntimeException("Maaş miktarı bilgisi boş olamaz!");
        } else if(ValidationUtils.isValueNull(salarySaveDto.getSalaryYear()) || salarySaveDto.getSalaryYear() == 0) {
            throw new RuntimeException("Maaş yılı bilgisi boş olamaz!");
        } else if(ValidationUtils.isValueNull(salarySaveDto.getSalaryMonth()) || salarySaveDto.getSalaryMonth() == 0) {
            throw new RuntimeException("Maaş ayı bilgisi boş olamaz!");
        }
    }

    private void isThereSalaryInMonthAndYear(Month salaryMonth, int salaryYear) {
        if(salaryRepository.existsBySalaryMonthAndSalaryYear(salaryMonth, salaryYear)) {
            throw new RuntimeException(salaryMonth.getDisplayName(TextStyle.FULL, new Locale("tr", "TR"))
            + "-" + salaryYear + " ayına ait bir maaş bilgisi bulunmaktadır.");
        }
    }
}
