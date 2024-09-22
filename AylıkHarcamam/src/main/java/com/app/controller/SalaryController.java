package com.app.controller;

import com.app.dto.SalarySaveDto;
import com.app.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/salary")
public class SalaryController {

    private final SalaryService salaryService;

    @PostMapping
    public ResponseEntity<String> saveSalary(@RequestBody SalarySaveDto salarySaveDto) {
        return new ResponseEntity<>(salaryService.saveSalary(salarySaveDto), HttpStatus.OK);
    }
}
