package com.app.controller;

import com.app.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/file")
public class FileController {

    /*private final FileService fileService;

    @GetMapping("/download")
    public ResponseEntity<FileSystemResource> downloadFile(@RequestParam String fileName) {
       var resource = fileService.downloadDebtFile(fileName);

       if(resource == null) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }

       return ResponseEntity.ok()
              // .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
               .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + fileName + "\"")
               .body(resource);


       //.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
    }

    @GetMapping("/deneme")
    public ResponseEntity<FileSystemResource> deneme() {
        System.out.println("Geldim burayaaaa...");
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }*/
}


//response 200 amadosya inmiyor çünkü kaydedilmiyor

/*  FileSystemResource resource = fileService.getFile(fileName); // Service metodunu çağır

        if (resource == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(resource);*/
