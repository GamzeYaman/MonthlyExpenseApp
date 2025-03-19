package com.app.service;

import com.app.common.Parameter;
import com.app.common.client.MailClientApi;
import com.app.common.utils.DebtUtils;
import com.app.common.utils.FileUtils;
import com.app.dto.MonthlyDebt;
import com.app.entity.Salary;
import com.app.repository.DebtRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class FileService {

    private final DebtRepository debtRepository;
    private final MailClientApi mailClientApi;


    public File createFile(String fileName) {
        try {
            StringBuilder sbFileName = new StringBuilder(fileName);
            sbFileName.append(".txt");

            String directory = Parameter.DEBT_FILE_DIRECTORY;
            Path directoryPath = Paths.get(directory);

            log.debug("Dosya oluşturulmaya başlandı..");
            Path filePath = directoryPath.resolve(sbFileName.toString());

            if(!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            File newFile = Files.createFile(filePath).toFile();
            log.debug(MessageFormat.format("{0} isimli dosya başarıyla oluşturuldu.", fileName));

            return newFile;
        } catch (IOException e) {
            log.error("Dosya oluşturulurken bir hata oluştu!");
            throw new RuntimeException("File oluşturulurken hata oluştu", e);
        }
    }


    public void writeFile(File file, String citizenId, Salary salary) throws IOException { //TODO exception sonrasında düzeltilecek
        try(BufferedWriter bufferedWriter = FileUtils.generateWriter(file)) {
            List<MonthlyDebt> monthlyDebtList = debtRepository.findByCitizenIdAndDebtMonthAndDebtYear(citizenId, salary.getSalaryMonth(), (short) salary.getSalaryYear());
            HashMap<String, BigDecimal> monthlyDebtHashMap = DebtUtils.getDebtNameAndAmountList(monthlyDebtList);

            FileUtils.writeLine(bufferedWriter, FileUtils.writeExtraLine("Total Maaş", salary.getSalaryAmount()));
            writeDebtLine(bufferedWriter, monthlyDebtHashMap);
            FileUtils.writeLine(bufferedWriter, FileUtils.writeExtraLine("Geriye Kalan Görevsiz Para", getRemainingMoney(salary, monthlyDebtList)));

        } catch (Exception e) {

        }
    }

    public void sendFileByMail() { //TODO burası geliştirilecek
        log.info("Mail gönderilme işlemi yapılıyor.");
        mailClientApi.sendBatchMail();
    }

    private void writeDebtLine(BufferedWriter bufferedWriter, HashMap<String, BigDecimal> monthlyDebtHashMap) {
        monthlyDebtHashMap.forEach((debtType, debtAmount) -> FileUtils.writeLine(bufferedWriter, FileUtils.createDebtLine(debtType, debtAmount)));
    }

    private BigDecimal getRemainingMoney(Salary salary, List<MonthlyDebt> monthlyDebtList) {
        return DebtUtils.calculateRemainingMoneyInSalary(salary, DebtUtils.calculateTotalDebtAmount(monthlyDebtList));
    }
}
