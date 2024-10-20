package com.app.controller;

import com.app.common.RestResponse;
import com.app.dto.DebtDto;
import com.app.service.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/debt")
public class DebtController {

    private final DebtService debtService;

    @GetMapping("/")
    public ResponseEntity<RestResponse<List<DebtDto>>> getDebtListByCitizenId(@RequestParam String citizenId){
        return ResponseEntity.ok(RestResponse.of(debtService.listDebtsByCitizenId(citizenId)));
    }

    @GetMapping("/{debtTypeValue}")
    public ResponseEntity<RestResponse<List<DebtDto>>> getDebtListByDebtType(@PathVariable int debtTypeValue){
        return ResponseEntity.ok(RestResponse.of(debtService.listDebtsByDebtType(debtTypeValue)));
    }
}
