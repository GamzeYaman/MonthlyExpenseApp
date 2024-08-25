package com.app.controller;

import com.app.dto.CitizenSaveDto;
import com.app.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/citizen/")
public class CitizenController {

    private final CitizenService citizenService;

    @PostMapping("/save")
    public ResponseEntity<String> saveCitizen(@RequestBody CitizenSaveDto citizenSaveDto) {
        return new ResponseEntity<>(citizenService.saveCitizen(citizenSaveDto), HttpStatus.OK);
    }

}
