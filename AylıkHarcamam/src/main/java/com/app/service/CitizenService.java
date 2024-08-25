package com.app.service;

import com.app.dto.CitizenSaveDto;
import org.springframework.stereotype.Service;

@Service
public interface CitizenService {

    String saveCitizen(CitizenSaveDto citizenSaveDto);


}
