package com.app.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class DebtQueryService {
    private final Map<String, DebtQueryStrategy> strategyMap;

    public DebtQueryService(Map<String, DebtQueryStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    public String getQueryResult(String debtTypeStrategy, Long citizenNo) throws JsonProcessingException {
        return Optional.ofNullable(strategyMap.get(debtTypeStrategy))
                .orElseThrow(() -> new IllegalArgumentException("Geçersiz Borç Türü: " + debtTypeStrategy))
                .getDebtResult(citizenNo);
    }
}
