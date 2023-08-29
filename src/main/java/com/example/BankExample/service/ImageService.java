package com.example.BankExample.service;

import com.example.BankExample.DTO.ImageDTO;
import com.example.BankExample.DTO.ImageDownloadDTO;

public interface ImageService {
    ImageDTO uploadFile(ImageDTO imageDTO);

    ImageDTO uploadFileWithUserId(int id, ImageDTO imageDTO);

    ImageDownloadDTO downloadFile(Long imageId);
}
