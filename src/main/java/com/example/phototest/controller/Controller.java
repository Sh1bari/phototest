package com.example.phototest.controller;

import com.example.phototest.models.MainModel;
import com.example.phototest.services.MainModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("files")
public class Controller {
    private final MainModelService mainModelService;

    public Controller(MainModelService mainModelService) {
        this.mainModelService = mainModelService;
    }

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            mainModelService.save(file);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {
        Optional<MainModel> fileEntityOptional = mainModelService.getFile(id);

        if (fileEntityOptional.isEmpty()) {
            return ResponseEntity.notFound()
                    .build();
        }

        MainModel fileEntity = fileEntityOptional.get();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(fileEntity.getContentType()))
                .body(fileEntity.getData());
    }

    @GetMapping("/hi")
    public String hi(){
        return "hi";
    }
}
