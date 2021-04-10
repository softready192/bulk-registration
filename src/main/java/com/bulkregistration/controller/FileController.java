package com.bulkregistration.controller;

import com.bulkregistration.processor.FileProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    private final FileProcessor fileProcessor;

    @Autowired
    public FileController(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        try {
            if (!file.getOriginalFilename().endsWith(".csv")) {
                return ResponseEntity.badRequest().body("File format should be CSV only.");
            }
            fileProcessor.process(file.getInputStream());
            return ResponseEntity.ok("File processed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
