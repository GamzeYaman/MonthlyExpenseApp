package com.app.converter;

import com.app.common.mapper.CommonMapper;
import com.app.dto.DetailOfSalaryArrangementDto;
import com.app.dto.SalarySaveDto;
import com.app.entity.Salary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.HashMap;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = CommonMapper.class
)
public interface SalaryMapper {

    SalaryMapper INSTANCE = Mappers.getMapper(SalaryMapper.class);

    @Mapping(target = "salaryMonth", source = "salaryMonth", qualifiedByName = "getMonth")
    @Mapping(target = "citizen.id", source = "citizenId")
    Salary convertToSalaryFromSalarySaveDto(SalarySaveDto salarySaveDto);

    @Mappings(value = {
         @Mapping(target = "month", source = "salary.salaryMonth", qualifiedByName = "getMonthName"),
         @Mapping(target = "year", source = "salary.salaryYear"),
         @Mapping(target = "totalDebtAmount", source = "totalDebtAmount"),
         @Mapping(target = "remainingSalary", source = "remainingSalary"),
         @Mapping(target = "debtListOfCitizen", source = "debtNameAndAmountList"),
    })
    DetailOfSalaryArrangementDto convertToDetailOfSalaryArrangementDto(Salary salary, BigDecimal totalDebtAmount, BigDecimal remainingSalary, HashMap<String, BigDecimal> debtNameAndAmountList);
}
