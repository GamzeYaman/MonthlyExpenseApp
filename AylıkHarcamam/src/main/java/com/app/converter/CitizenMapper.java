package com.app.converter;

import com.app.dto.CitizenDto;
import com.app.dto.CitizenSaveDto;
import com.app.dto.CitizenUpdateDto;
import com.app.entity.Citizen;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CitizenMapper {

    CitizenMapper INSTANCE = Mappers.getMapper(CitizenMapper.class);

    Citizen convertCitizenFromCitizenSaveDto(CitizenSaveDto citizenSaveDto);

    void updateCitizenFromDto(CitizenUpdateDto citizenUpdateDto, @MappingTarget Citizen citizen);

    CitizenDto convertCitizenDtoFromCitizen(Citizen citizen);
}
