package com.app.service;

import com.app.dto.DebtDto;
import com.app.dto.DebtSearchDto;
import com.app.dto.FileNameRequest;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

public interface DebtService {

    List<DebtDto> listDebtsByCitizenId(String citizenId);

    List<DebtDto> listDebtsByCriterias(DebtSearchDto debtSearchDto);

    String createDebtFile(FileNameRequest request);

}

