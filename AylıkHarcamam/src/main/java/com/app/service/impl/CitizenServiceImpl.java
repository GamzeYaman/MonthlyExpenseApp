package com.app.service.impl;

import com.app.common.validation.ValidationUtils;
import com.app.converter.CitizenMapper;
import com.app.dto.CitizenSaveDto;
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
        return "Kişi başarıyla eklenmiştir. Ad Soyad / Kullanıcı Adı : " + citizen.getCitizenName() + " " + citizen.getCitizenSurname() + " / " + citizen.getUsername();
    }

    private void isUsernameUsed(String username) {
        Citizen citizen = citizenRepository.findByUsername(username);
        if(citizen != null) {
            throw new RuntimeException(username + " kullanıcı adı ile bir kullanıcı mevcuttur!");
        }
    }

    private void isDtoValuesNull(CitizenSaveDto citizenSaveDto) {
        if(ValidationUtils.isValueNull(citizenSaveDto.getCitizenName())) {
            throw new RuntimeException("Kişi adı boş olamaz!");
        } else if(ValidationUtils.isValueNull(citizenSaveDto.getCitizenSurname())) {
            throw new RuntimeException("Kişi soyadı boş olamaz");
        } else if(ValidationUtils.isValueNull(citizenSaveDto.getUsername())) {
            throw new RuntimeException("Kişi kullanıcı adı boş olamaz!");
        } else if(ValidationUtils.isValueNull(citizenSaveDto.getPassword())) {
            throw new RuntimeException("Şifre boş olamaz!");
        }
    }

}
