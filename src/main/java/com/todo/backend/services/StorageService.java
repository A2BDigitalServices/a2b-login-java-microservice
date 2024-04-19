package com.todo.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.todo.backend.entities.ImageData;
import com.todo.backend.util.ImageUtils;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private com.todo.backend.repo.StorageRepository repository;

    public String uploadImage(MultipartFile file) throws IOException {

        com.todo.backend.entities.ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
