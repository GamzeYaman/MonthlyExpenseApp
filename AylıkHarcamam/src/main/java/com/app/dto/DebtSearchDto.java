package com.app.dto;

import com.app.common.enums.DebtType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Month;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DebtSearchDto {
    private int debtTypeValue;
    private int debtMonthValue;
    private short debtYear;

    public DebtType getDebtType() {
        return DebtType.getDebtTypeByValue(debtTypeValue);
    }

    public Month getDebtMonth() {
        return Month.of(debtMonthValue);
    }

}
