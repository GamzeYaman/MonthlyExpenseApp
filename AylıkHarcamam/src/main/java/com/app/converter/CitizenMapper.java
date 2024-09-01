package com.app.converter;

import com.app.dto.CitizenDto;
import com.app.dto.CitizenSaveDto;
import com.app.entity.Citizen;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CitizenMapper {

    CitizenMapper INSTANCE = Mappers.getMapper(CitizenMapper.class);

    Citizen convertCitizenFromCitizenSaveDto(CitizenSaveDto citizenSaveDto);
    CitizenDto convertCitizenDtoFromCitizen(Citizen citizen);
}
