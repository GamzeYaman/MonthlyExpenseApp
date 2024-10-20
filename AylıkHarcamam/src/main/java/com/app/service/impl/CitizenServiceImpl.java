package com.app.service.impl;

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
        Citizen citizen = citizenRepository.findById(citizenId).orElseThrow(() -> { return new RuntimeException("İgili kişi bulunamadı!");});
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
            throw new RuntimeException(username + " kullanıcı adı ile bir kullanıcı mevcuttur!");
        }
    }

    private void isDtoValuesNull(CitizenSaveDto citizenSaveDto) {
        validationOfValues(citizenSaveDto.getCitizenName(), citizenSaveDto.getCitizenSurname(), citizenSaveDto.getUsername());
         if(ValidationUtils.isValueNull(citizenSaveDto.getPassword())) {
            throw new RuntimeException("Şifre boş olamaz!");
        }
    }

    private void validationOfValues(String citizenName, String citizenSurname, String username) {
        if(ValidationUtils.isValueNull(citizenName)) {
            throw new RuntimeException("Kişi adı boş olamaz!");
        } else if(ValidationUtils.isValueNull(citizenSurname)) {
            throw new RuntimeException("Kişi soyadı boş olamaz");
        } else if(ValidationUtils.isValueNull(username)) {
            throw new RuntimeException("Kişi kullanıcı adı boş olamaz!");
        }
    }

    private String getSavedCitizenInfo(Citizen citizen) {
        return citizen.getCitizenName() + " " + citizen.getCitizenSurname() + " / " + citizen.getUsername();
    }

}
