package com.app.service.impl;

import com.app.common.Parameter;
import com.app.common.validation.ValidationUtils;
import com.app.converter.SalaryMapper;
import com.app.dto.SalarySaveDto;
import com.app.entity.Salary;
import com.app.repository.SalaryRepository;
import com.app.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository salaryRepository;

    @Override
    public String saveSalary(SalarySaveDto salarySaveDto) {
        isDtoValuesNull(salarySaveDto);
        isThereSalaryInMonthAndYear(Month.of(salarySaveDto.getSalaryMonth()), salarySaveDto.getSalaryYear());
        Salary salary = SalaryMapper.INSTANCE.convertToSalaryFromSalarySaveDto(salarySaveDto);
        salary = salaryRepository.save(salary);
        return getSavedSalaryInfo(salary);
    }

    private String getSavedSalaryInfo(Salary salary) {
        return Parameter.CURRENCY_FORMATTER.format(salary.getSalaryAmount()) + "/"
                + salary.getSalaryYear() + "-"
                + salary.getSalaryMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("tr"));
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
