package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalarySaveDto {
    private BigDecimal salaryAmount;
    private int salaryYear;
    private int salaryMonth;
    private String citizenId; //TODO security eklenince sessiondan alınabilir
}
