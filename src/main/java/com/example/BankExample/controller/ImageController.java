package com.example.BankExample.controller;

import com.example.BankExample.DTO.ImageDTO;
import com.example.BankExample.DTO.ImageDownloadDTO;
import com.example.BankExample.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("upload")
    public ResponseEntity<ImageDTO> upload(@ModelAttribute ImageDTO imageDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.imageService.uploadFile(imageDTO));
    }

    @PostMapping("upload/user/{id}")
    public ResponseEntity<ImageDTO> uploadWithUserId(@PathVariable int id, @ModelAttribute ImageDTO imageDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.imageService.uploadFileWithUserId(id,imageDTO));
    }

    @GetMapping("download/{Id}")
    public ResponseEntity<Resource> download(@PathVariable("Id") Long imageId) {
        ImageDownloadDTO imageDownloadDTO = this.imageService.downloadFile(imageId);
        return ResponseEntity.ok()
                .contentType(imageDownloadDTO.getMediaType())
                .body(imageDownloadDTO.getResource());
    }

    @GetMapping("download/user/{id}")
    public ResponseEntity<Resource> downloadWithUserId(@PathVariable("id") int id){
        ImageDownloadDTO imageDownloadDTO = this.imageService.downloadWithUserId(id);
        return ResponseEntity.ok()
                .contentType(imageDownloadDTO.getMediaType())
                .body(imageDownloadDTO.getResource());
    }
}
