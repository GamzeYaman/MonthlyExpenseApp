package com.app.consumer.controller;

import com.app.consumer.service.impl.ElectricityBillStrategy;
import com.app.consumer.service.impl.GasBillStrategy;
import com.app.consumer.service.impl.PhoneBillStrategy;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("deneme/")
public class DenemeController {

    private final PhoneBillStrategy waterBillStrategy;

    public DenemeController(PhoneBillStrategy waterBillStrategy) {
        this.waterBillStrategy = waterBillStrategy;
    }

    @GetMapping
    public String deneme() throws JsonProcessingException {
        return waterBillStrategy.getDebtResult(54545454545L);
    }
}
