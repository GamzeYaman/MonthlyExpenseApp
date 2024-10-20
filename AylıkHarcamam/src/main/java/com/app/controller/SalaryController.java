package com.app.controller;

import com.app.common.RestResponse;
import com.app.dto.DetailOfSalaryArrangementDto;
import com.app.dto.SalarySaveDto;
import com.app.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/salary")
public class SalaryController {

    private final SalaryService salaryService;

    @PostMapping
    public ResponseEntity<RestResponse<String>> saveSalary(@RequestBody SalarySaveDto salarySaveDto) {
        return ResponseEntity.ok(RestResponse.of(salaryService.saveSalary(salarySaveDto)));
    }

    @PutMapping("/{salaryId}")
    public ResponseEntity<RestResponse<String>> updateSalary(@PathVariable Long salaryId, @RequestBody SalarySaveDto salarySaveDto) {
        return ResponseEntity.ok(RestResponse.of(salaryService.updateSalary(salaryId, salarySaveDto)));
    }

    @GetMapping
    public ResponseEntity<RestResponse<DetailOfSalaryArrangementDto>> getArrangementOfSalary(@RequestParam String citizenId, @RequestParam Month debtMonth, @RequestParam short debtYear) { //TODO parametreler dto olarak alınabilir
        return ResponseEntity.ok(RestResponse.of(salaryService.showArrangementOfSalary(citizenId, debtMonth, debtYear)));
    }
}
