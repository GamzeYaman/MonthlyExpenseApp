package com.app.service;

import com.app.common.Parameter;
import com.sun.jdi.event.ExceptionEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;

@Log4j2
@Service
public class FileService {

    public File createFile(String fileName) {
        try {
            StringBuilder sbFileName = new StringBuilder(fileName);
            sbFileName.append(".pdf");

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

    public void sendRequestToDownloadFileProcess(String fileName) {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();

        try {
           /* webClient.get()
                    .uri("http://localhost:8080/v1/file/deneme")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();*/

            webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("http")
                            .host("localhost")
                            .port(8080)
                            .path("/v1/file/download")
                            .queryParam("fileName", URLEncoder.encode(fileName, StandardCharsets.UTF_8))
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            /*webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/v1/file/download/{fileName}")
                            .build(fileName))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();*/
        } catch (Exception e) {
            //TODO exception özelleştirilmeli
            e.printStackTrace();
        }

        //TODO localhostu düzenle

    }

    public FileSystemResource downloadDebtFile(String fileName) {
        File file = new File(Parameter.DEBT_FILE_DIRECTORY, fileName);

        return file.exists() ? new FileSystemResource(file) : null;
    }


    //todo file indirme

    //TODO file yazma
}
