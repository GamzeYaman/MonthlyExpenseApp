package com.app.converter;

import com.app.common.mapper.CommonMapper;
import com.app.dto.DebtDto;
import com.app.entity.Debt;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = CommonMapper.class
)
public interface DebtMapper {

    DebtMapper INSTANCE = Mappers.getMapper(DebtMapper.class);

    @Mapping(target = "debtType", source = "debtType", qualifiedByName = "enumToString")
    @Mapping(target = "debtMonth", source = "debtMonth", qualifiedByName = "getMonthName")
    @Mapping(target = "debtAmount", source = "debtAmount", qualifiedByName = "getMoneyInFormatted")
    DebtDto debtDtoFromDebt(Debt debt);

}
