package com.app.consumer.service.impl;

import com.app.consumer.common.DebtUtils;
import com.app.consumer.common.dto.WebServiceResponse;
import com.app.consumer.common.enums.DebtType;
import com.app.consumer.service.DebtQueryStrategy;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("InternetBill")
public class InternetBillStrategy implements DebtQueryStrategy {
    @Override
    public String getDebtResult(Long citizenNo) throws JsonProcessingException {
        BigDecimal billAmount = DebtUtils.getRandomDebtAmountForUtilityBills();
        return DebtUtils.getObjMapInstance().writeValueAsString(new WebServiceResponse(billAmount, DebtType.INTERNET_FATURASI.getVal()));
    }
}
