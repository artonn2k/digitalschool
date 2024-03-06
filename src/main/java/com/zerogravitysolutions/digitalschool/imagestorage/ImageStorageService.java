package com.zerogravitysolutions.digitalschool.imagestorage;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {

    String saveImage(MultipartFile file, String existingFileName);
}
