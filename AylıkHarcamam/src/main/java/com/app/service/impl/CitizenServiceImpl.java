package com.app.service.impl;

import com.app.common.exception.exceptionmessage.GeneralExceptionMessages;
import com.app.common.exception.exceptions.AlreadyHaveException;
import com.app.common.exception.exceptions.InvalidDataException;
import com.app.common.exception.exceptions.ItemNotFoundException;
import com.app.common.validation.ValidationUtils;
import com.app.converter.CitizenMapper;
import com.app.dto.CitizenDto;
import com.app.dto.CitizenSaveDto;
import com.app.dto.CitizenUpdateDto;
import com.app.entity.Citizen;
import com.app.repository.CitizenRepository;
import com.app.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CitizenServiceImpl implements CitizenService {

    private final CitizenRepository citizenRepository;

    @Override
    public String saveCitizen(CitizenSaveDto citizenSaveDto) {
        isDtoValuesNull(citizenSaveDto);
        isUsernameUsed(citizenSaveDto.getUsername());
        Citizen citizen = CitizenMapper.INSTANCE.convertCitizenFromCitizenSaveDto(citizenSaveDto);
        citizenRepository.save(citizen);
        return "Kişi başarıyla eklenmiştir. Ad Soyad / Kullanıcı Adı : " + getSavedCitizenInfo(citizen);
    }

    @Override
    public String updateCitizen(String citizenId, CitizenUpdateDto citizenUpdateDto) {
        validationOfValues(citizenUpdateDto.getCitizenName(), citizenUpdateDto.getCitizenSurname(), citizenUpdateDto.getUsername());
        isUsernameUsed(citizenUpdateDto.getUsername());
        Citizen citizen = citizenRepository.findById(citizenId).orElseThrow(() -> new ItemNotFoundException(GeneralExceptionMessages.USER_NOT_FOUND.getMessage()));
        CitizenMapper.INSTANCE.updateCitizenFromDto(citizenUpdateDto, citizen);
        citizenRepository.save(citizen);
        return "Kişi başarıyla güncellenmiştir. Ad Soyad / Kullanıcı Adı : " + getSavedCitizenInfo(citizen);
    }

    @Override
    public CitizenDto getCitizenById(String citizenId) {
        return citizenRepository.findById(citizenId)
                .map(CitizenMapper.INSTANCE::convertCitizenDtoFromCitizen)
                .orElse(null)
                ;
    }

    private void isUsernameUsed(String username) {
        Citizen citizen = citizenRepository.findByUsername(username);
        if(citizen != null) {
            throw new AlreadyHaveException(username + GeneralExceptionMessages.USERNAME_ALREADY_USED.getMessage());
        }
    }

    private void isDtoValuesNull(CitizenSaveDto citizenSaveDto) {
        validationOfValues(citizenSaveDto.getCitizenName(), citizenSaveDto.getCitizenSurname(), citizenSaveDto.getUsername());
         if(ValidationUtils.isValueNull(citizenSaveDto.getPassword())) {
            throw new InvalidDataException("Şifre " + GeneralExceptionMessages.EMPTY_DATA.getMessage());
        }
    }

    private void validationOfValues(String citizenName, String citizenSurname, String username) {
        if(ValidationUtils.isValueNull(citizenName)) {
            throw new InvalidDataException("Kişi adı " + GeneralExceptionMessages.EMPTY_DATA.getMessage());
        } else if(ValidationUtils.isValueNull(citizenSurname)) {
            throw new InvalidDataException("Kişi soyadı " + GeneralExceptionMessages.EMPTY_DATA.getMessage());
        } else if(ValidationUtils.isValueNull(username)) {
            throw new InvalidDataException("Kişi kullanıcı adı " + GeneralExceptionMessages.EMPTY_DATA.getMessage());
        }
    }

    private String getSavedCitizenInfo(Citizen citizen) {
        return citizen.getCitizenName() + " " + citizen.getCitizenSurname() + " / " + citizen.getUsername();
    }

}
