package com.app.service;

import com.app.dto.DebtDto;

import java.util.List;

public interface DebtService {

    List<DebtDto> listDebtsByCitizenId(String citizenId);

    List<DebtDto> listDebtsByDebtType(int debtTypeValue);
}
