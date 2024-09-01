package com.app.service.impl;

import com.app.common.enums.DebtType;
import com.app.converter.DebtMapper;
import com.app.dto.DebtDto;
import com.app.repository.DebtRepository;
import com.app.service.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DebtServiceImpl implements DebtService {

    private final DebtRepository debtRepository;

    @Override
    public List<DebtDto> listDebtsByCitizenId(String citizenId) {
        return debtRepository.findByCitizenId(citizenId).stream()
                .map(DebtMapper.INSTANCE::debtDtoFromDebt)
                .toList();
    }

    @Override
    public List<DebtDto> listDebtsByDebtType(int debtTypeValue) {
        return debtRepository.findByDebtType(DebtType.getDebtTypeByValue(debtTypeValue)).stream()
                .map(DebtMapper.INSTANCE::debtDtoFromDebt)
                .toList();
    }
}
