package com.zerogravitysolutions.digitalschool.imagestorage;

import com.zerogravitysolutions.digitalschool.configs.ImageProperties;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {

    private ImageProperties imageProperties;

    public ImageStorageServiceImpl(ImageProperties imageProperties) {
        this.imageProperties = imageProperties;
    }

    @Override
    public String saveImage(MultipartFile file, String existingFileName) {

        try {

            BufferedImage originalImage = ImageIO.read(file.getInputStream());
            String fileName;

            if (existingFileName != null && !existingFileName.isEmpty()) {
                fileName = existingFileName;
            } else {
                fileName = generateUniqueFileName(Objects.requireNonNull(file.getOriginalFilename()));
            }

            for (ImageSize imageSize : ImageSize.values()) {

                // image/large/1414123412313123.jpeg
                String imagePath = imageProperties.getStoragePath() + imageSize.name().toLowerCase() + "/" + fileName;

                BufferedImage resizedImage = resizeImage(originalImage, imageSize);
                saveImageToStorage(resizedImage, imagePath);
            }

            return fileName;

        } catch (IllegalArgumentException iae) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid image", iae);

        } catch (IOException ioe) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save the image");
        }

    }

    private String generateUniqueFileName(String originalFileName) {

        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        //Generate random UUID
        UUID uuid = UUID.randomUUID();

        return uuid.toString() + fileExtension;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, ImageSize imageSize) {

        int targetWidth = imageProperties.getTargetWidth(imageSize);
        int targetHeight = imageProperties.getTargetHeight(imageSize);

        if (targetWidth == 0 || targetHeight == 0) {
            return originalImage;
        }

        try{

            BufferedImage resizedImage = Thumbnails.of(originalImage)
                    .size(targetWidth, targetHeight)
                    .keepAspectRatio(true)
                    .asBufferedImage();

            return resizedImage;

        }catch (IllegalArgumentException iae){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid image", iae);
        }catch (IOException ioe){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save the image", ioe);

        }
    }

    private  void saveImageToStorage(BufferedImage image, String imagePath){

        Path path = Paths.get(imagePath);

        try{

            Files.createDirectories(path.getParent());

            ImageIO.write(image,"jpeg", path.toFile());

        }catch (SecurityException se){

            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Permission not allowed to save image to selected  path" , se);

        }catch (DirectoryNotEmptyException dnee){

            throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error" , dnee);

        }catch (IOException ioe){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save the image", ioe);
        }
    }
}
