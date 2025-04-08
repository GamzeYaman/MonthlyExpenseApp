package com.app.service.impl;

import com.app.converter.DebtMapper;
import com.app.dto.DebtDto;
import com.app.dto.DebtSearchDto;
import com.app.dto.FileNameRequest;
import com.app.entity.Salary;
import com.app.repository.DebtRepository;
import com.app.repository.SalaryRepository;
import com.app.service.DebtService;
import com.app.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
@Log4j2
@RequiredArgsConstructor
public class DebtServiceImpl implements DebtService {

    private final DebtRepository debtRepository;
    private final SalaryRepository salaryRepository;
    private final FileService fileService;

    @Override
    public List<DebtDto> listDebtsByCitizenId(String citizenId) {
        return debtRepository.findByCitizenId(citizenId).stream()
                .map(DebtMapper.INSTANCE::debtDtoFromDebt)
                .toList();
    }

    @Override
    public List<DebtDto> listDebtsByCriterias(DebtSearchDto debtSearchDto) {
        Pageable pageable = PageRequest.of(debtSearchDto.getPageNumber(), debtSearchDto.getPageSize());
        return debtRepository.searchDebtByCriterias(debtSearchDto, pageable).stream()
                .map(DebtMapper.INSTANCE::debtDtoFromDebt)
                .toList();
    }

    @Override
    public String createDebtFile(FileNameRequest request) throws IOException { //TODO username tokendan alınabiir, exception düzeltilmeli
        Salary salary = salaryRepository.findById(request.salaryId()).orElseThrow(() -> new RuntimeException("Maaş bilgisi bulunamamıştır!"));
        String fileName = request.username() + salary.getSalaryYear() + salary.getSalaryMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("tr"));
        File newFile = fileService.createFile(fileName);
        fileService.writeFile(newFile, request.citizenId(), salary);
        File pdfFile = fileService.convertToPdf(newFile);
        fileService.sendFileByMail();

        log.info("Txt dosyası: " + newFile + " / " + "Pdf dosyası: " + pdfFile);
        return fileName;
    }

}
