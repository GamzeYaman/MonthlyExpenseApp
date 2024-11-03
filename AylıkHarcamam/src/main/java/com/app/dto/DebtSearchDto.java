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
    private Short debtYear;
    private Month debtMonth;
    private DebtType debtType;
}
