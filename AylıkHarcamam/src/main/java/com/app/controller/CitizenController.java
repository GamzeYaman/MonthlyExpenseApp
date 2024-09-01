package com.app.controller;

import com.app.dto.CitizenDto;
import com.app.dto.CitizenSaveDto;
import com.app.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/citizen/")
public class CitizenController {

    private final CitizenService citizenService;

    @PostMapping("/save")
    public ResponseEntity<String> saveCitizen(@RequestBody CitizenSaveDto citizenSaveDto) {
        return new ResponseEntity<>(citizenService.saveCitizen(citizenSaveDto), HttpStatus.OK);
    }

    @GetMapping("/getCitizen")
    public ResponseEntity<CitizenDto> getCitizenbyId(@RequestParam String citizenId) {
        return new ResponseEntity<>(citizenService.getCitizenById(citizenId), HttpStatus.OK);
    }
}
