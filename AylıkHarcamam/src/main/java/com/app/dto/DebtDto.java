package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DebtDto {
    private Long id;
    private String debtType;
    private String debtAmount;
    private String debtMonth;
    private short debtYear;
}
