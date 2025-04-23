package com.app.consumer.common.dto;

import java.math.BigDecimal;

public record WebServiceResponse(BigDecimal amount, int debtType) {
}
