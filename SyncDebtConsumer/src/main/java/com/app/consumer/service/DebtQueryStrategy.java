package com.app.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface DebtQueryStrategy {

    String getDebtResult(Long citizenNo) throws JsonProcessingException;
}
