package com.app.service;

import com.app.dto.CitizenDto;
import com.app.dto.CitizenSaveDto;
import com.app.dto.CitizenUpdateDto;
import org.springframework.stereotype.Service;

@Service
public interface CitizenService {

    String saveCitizen(CitizenSaveDto citizenSaveDto);

    String updateCitizen(String citizenId, CitizenUpdateDto citizenUpdateDto);

    CitizenDto getCitizenById(String citizenId);
}
