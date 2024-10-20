package com.app.controller;

import com.app.dto.DetailOfSalaryArrangementDto;
import com.app.dto.SalarySaveDto;
import com.app.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/salary")
public class SalaryController {

    private final SalaryService salaryService;

    @PostMapping
    public ResponseEntity<String> saveSalary(@RequestBody SalarySaveDto salarySaveDto) {
        return new ResponseEntity<>(salaryService.saveSalary(salarySaveDto), HttpStatus.OK);
    }

    @PutMapping("/{salaryId}")
    public ResponseEntity<String> updateSalary(@PathVariable Long salaryId, @RequestBody SalarySaveDto salarySaveDto) {
        return new ResponseEntity<>(salaryService.updateSalary(salaryId, salarySaveDto), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<DetailOfSalaryArrangementDto> getArrangementOfSalary(@RequestParam String citizenId, @RequestParam Month debtMonth, @RequestParam short debtYear) {
        return new ResponseEntity<>(salaryService.showArrangementOfSalary(citizenId, debtMonth, debtYear), HttpStatus.OK);
    }
}
