package com.app.consumer.service.impl;

import com.app.consumer.common.DebtUtils;
import com.app.consumer.common.dto.WebServiceResponse;
import com.app.consumer.common.enums.DebtType;
import com.app.consumer.service.DebtQueryStrategy;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WaterBillStrategy implements DebtQueryStrategy {
    @Override
    public String getDebtResult(Long citizenNo) throws JsonProcessingException {
        BigDecimal waterBillAmount = DebtUtils.getRandomDebtAmountForUtilityBills();  //This is like that you call web service and get response...
        return DebtUtils.getObjMapInstance()
                .writeValueAsString(new WebServiceResponse(waterBillAmount, DebtType.SU_FATURASI.getVal()));
    }
}
