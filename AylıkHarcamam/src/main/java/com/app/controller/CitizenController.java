package com.app.controller;

import com.app.common.RestResponse;
import com.app.dto.CitizenDto;
import com.app.dto.CitizenSaveDto;
import com.app.dto.CitizenUpdateDto;
import com.app.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/citizen/")
public class CitizenController {

    private final CitizenService citizenService;

    @PostMapping
    public ResponseEntity<RestResponse<String>> saveCitizen(@RequestBody CitizenSaveDto citizenSaveDto) {
        return ResponseEntity.ok(RestResponse.of(citizenService.saveCitizen(citizenSaveDto)));
    }

    @PutMapping("{citizenId}")
    public ResponseEntity<RestResponse<String>> updateCitizen(@PathVariable String citizenId,  @RequestBody CitizenUpdateDto citizenUpdateDto) {
        return ResponseEntity.ok(RestResponse.of(citizenService.updateCitizen(citizenId, citizenUpdateDto)));
    }

    @GetMapping
    public ResponseEntity<RestResponse<CitizenDto>> getCitizenById(@RequestParam String citizenId) {
        return ResponseEntity.ok(RestResponse.of(citizenService.getCitizenById(citizenId)));
    }
}
