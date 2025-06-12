package com.app.consumer.controller;

import com.app.consumer.common.dto.DebtQueryRequest;
import com.app.consumer.service.DebtQueryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/debtquery")
public class DebtQueryController {
    private final DebtQueryService debtQueryService;

    public DebtQueryController(DebtQueryService debtQueryService) {
        this.debtQueryService = debtQueryService;
    }

    @PostMapping
    public ResponseEntity<String> getResult(@RequestBody DebtQueryRequest debtQueryRequest) throws JsonProcessingException {
        return new ResponseEntity<>(debtQueryService.getQueryResult(debtQueryRequest.debtTypeStrategy(), debtQueryRequest.citizenNo()), HttpStatus.OK);
    }
}
