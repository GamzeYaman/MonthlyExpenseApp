package com.app.service.impl;

import com.app.converter.DebtMapper;
import com.app.dto.DebtDto;
import com.app.dto.DebtSearchDto;
import com.app.dto.FileNameRequest;
import com.app.repository.DebtRepository;
import com.app.service.DebtService;
import com.app.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class DebtServiceImpl implements DebtService {

    private final DebtRepository debtRepository;
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
    public String createDebtFile(FileNameRequest request) { //TODO username tokendan alınabiir
        String fileName = request.username() + request.salarayYear() + request.salaryMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("tr"));
        File newFile = fileService.createFile(fileName);
        fileService.sendRequestToDownloadFileProcess(newFile.getName());
        return fileName;
    }


    /* public FileSystemResource getFile(String fileName) {
        File file = new File(DIRECTORY + fileName);

        if (!file.exists()) {
            return null; // Dosya bulunamazsa null döndür
        }

        return new FileSystemResource(file);
    }*/

    /*  File file = new File(DIRECTORY + fileName);

        // Dosyanın var olup olmadığını kontrol et
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .body(new FileSystemResource(file));
    }*/
}
