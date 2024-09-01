package com.app.controller;

import com.app.dto.DebtDto;
import com.app.service.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/debt")
public class DebtController {

    private final DebtService debtService;

    @GetMapping("/listDebts")
    public ResponseEntity<List<DebtDto>> getDebtListByCitizenId(@RequestParam String citizenId){
        return new ResponseEntity<>(debtService.listDebtsByCitizenId(citizenId), HttpStatus.OK);
    }

    @GetMapping("/listDebts/{debtTypeValue}")
    public ResponseEntity<List<DebtDto>> getDebtListByDebtType(@PathVariable int debtTypeValue){
        return new ResponseEntity<>(debtService.listDebtsByDebtType(debtTypeValue), HttpStatus.OK);
    }
}
