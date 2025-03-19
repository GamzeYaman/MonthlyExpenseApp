package com.app.controller;

import com.app.common.RestResponse;
import com.app.dto.DebtDto;
import com.app.dto.DebtSearchDto;
import com.app.dto.FileNameRequest;
import com.app.service.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PostMapping("/")
    public ResponseEntity<RestResponse<List<DebtDto>>> getDebtListByDebtType(@RequestBody DebtSearchDto debtSearchDto){
        return ResponseEntity.ok(RestResponse.of(debtService.listDebtsByCriterias(debtSearchDto)));
    }

    @PostMapping("/file")
    public ResponseEntity<RestResponse<String>> createDebtFile(@RequestBody FileNameRequest request) throws IOException {
        return ResponseEntity.ok(RestResponse.of(debtService.createDebtFile(request)));
    }

}
