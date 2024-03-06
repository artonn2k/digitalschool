package com.zerogravitysolutions.digitalschool.imagestorage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

public interface ImageStorageService {

    String saveImage(MultipartFile file, String existingFileName);

    Resource loadImage(String filename, ImageSize imageSize);

    void delete(String fileName);
}
