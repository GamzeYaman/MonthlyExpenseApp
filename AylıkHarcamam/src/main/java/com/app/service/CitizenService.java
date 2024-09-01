package com.app.service;

import com.app.dto.CitizenDto;
import com.app.dto.CitizenSaveDto;
import com.app.entity.Citizen;
import org.springframework.stereotype.Service;

@Service
public interface CitizenService {

    String saveCitizen(CitizenSaveDto citizenSaveDto);

    CitizenDto getCitizenById(String citizenId);
}
