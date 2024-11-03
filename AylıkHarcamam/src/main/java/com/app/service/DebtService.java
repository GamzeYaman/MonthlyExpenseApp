package com.app.service;

import com.app.dto.DebtDto;
import com.app.dto.DebtSearchDto;

import java.util.List;

public interface DebtService {

    List<DebtDto> listDebtsByCitizenId(String citizenId);

    List<DebtDto> listDebtsByCriterias(DebtSearchDto debtSearchDto);
}

