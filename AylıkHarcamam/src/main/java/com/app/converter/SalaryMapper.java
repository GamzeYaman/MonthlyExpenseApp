package com.app.converter;

import com.app.common.mapper.CommonMapper;
import com.app.dto.SalarySaveDto;
import com.app.entity.Salary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = CommonMapper.class
)
public interface SalaryMapper {

    SalaryMapper INSTANCE = Mappers.getMapper(SalaryMapper.class);

    @Mapping(target = "salaryMonth", source = "salaryMonth", qualifiedByName = "getMonth")
    Salary convertToSalaryFromSalarySaveDto(SalarySaveDto salarySaveDto);
}
