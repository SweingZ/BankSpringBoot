package com.example.BankExample.service.impl;



import com.example.BankExample.DTO.ImageDTO;
import com.example.BankExample.DTO.ImageDownloadDTO;
import com.example.BankExample.model.Image;
import com.example.BankExample.repository.ImageRepo;
import com.example.BankExample.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageServiceImpl implements ImageService {

    @Value("${file.upload.path}")
    private String uploadPath;

    @Autowired
    private ImageRepo imageRepo;

    @Override
    public ImageDTO uploadFile(ImageDTO imageDTO) {
        MultipartFile file = imageDTO.getImage();
        if (file.isEmpty()) throw new RuntimeException("File not found");
        if (!this.isFileValid(file)) throw new RuntimeException("Unsupported format");

        String fileName = this.generateFileName(file);
        Image savedImage;

        try {
            Files.copy(file.getInputStream(), this.generateFilePath(fileName), StandardCopyOption.REPLACE_EXISTING);
            savedImage = this.imageRepo.save(new Image(fileName));
        } catch (IOException exception) {
            throw new RuntimeException("File upload error");
        }
        return ImageDTO.builder().imageId(savedImage.getImageId()).build();

    }

    @Override
    public ImageDownloadDTO downloadFile(Long imageId) {
        Image image = this.imageRepo.findById(imageId).orElseThrow(() -> new RuntimeException("Image not found"));
        try {
            MediaType mediaType = this.getMediaType(image.getFileName());
            Resource resource = new UrlResource(this.generateFilePath(image.getFileName()).toUri());
            return new ImageDownloadDTO(resource, mediaType);
        } catch (IOException exception) {
            throw new RuntimeException("File read error");
        }
    }

    private MediaType getMediaType(String fileName) {
        int index = fileName.lastIndexOf('.');
        String extension = fileName.substring(index + 1);

        if (extension.equals("png")) {
            return MediaType.IMAGE_PNG;
        } else if (extension.equals("jpeg") || extension.equals("jpg")) {
            return MediaType.IMAGE_JPEG;
        }
        throw new RuntimeException("Invalid Media Type");
    }

    private Boolean isFileValid(MultipartFile file) {
        return Objects.equals(file.getContentType(), "image/png") ||
                Objects.equals(file.getContentType(), "image/jpeg") ||
                Objects.equals(file.getContentType(), "image/jpg");
    }

    private Path generateFilePath(String fileName) {
        return Paths.get(uploadPath + File.separator + fileName).toAbsolutePath();
    }

    private String generateFileName(MultipartFile file) {
        return RandomStringUtils.randomAlphanumeric(10) + "-" + file.getOriginalFilename();
    }
}
