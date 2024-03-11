package com.zerogravitysolutions.digitalschool.imagestorage;

import com.zerogravitysolutions.digitalschool.configs.ImageProperties;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {

    private ImageProperties imageProperties;

    private final Logger logger = LoggerFactory.getLogger(ImageStorageServiceImpl.class);

    public ImageStorageServiceImpl(ImageProperties imageProperties) {
        this.imageProperties = imageProperties;
    }

    @Override
    public String saveImage(MultipartFile file, String existingFileName) {

        logger.info("Trying to save images on storage with this file name: {}", existingFileName);
        try {

            BufferedImage originalImage = ImageIO.read(file.getInputStream());
            String fileName;

            if (existingFileName != null && !existingFileName.isEmpty()) {

                logger.info("File name already existed and used as it is: {}", existingFileName);
                fileName = existingFileName;
            } else {

                fileName = generateUniqueFileName(Objects.requireNonNull(file.getOriginalFilename()));
                logger.info("A new file name has been generated {}", fileName);
            }

            for (ImageSize imageSize : ImageSize.values()) {

                // image/large/1414123412313123.jpeg
                String imagePath = imageProperties.getStoragePath() + imageSize.name().toLowerCase() + "/" + fileName;

                BufferedImage resizedImage = resizeImage(originalImage, imageSize);

                logger.info("Trying to save image with name {}, at path: {}", fileName, imagePath);
                saveImageToStorage(resizedImage, imagePath);
            }

            return fileName;

        } catch (IllegalArgumentException iae) {

            logger.error("Invalid image dimensions {}",iae);
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

    @Override
    public Resource loadImage(String filename, ImageSize imageSize) {

        // image/small/1231233123124134123.jpeg
        String imagePath = imageProperties.getStoragePath() + imageSize.name().toLowerCase() + "/" + filename;

        Path path = Paths.get(imagePath);

        try {

            Resource resource = new UrlResource(path.toUri());

            if(resource.exists() && resource.isReadable()){
                return resource;
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
            }
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to load image", e);
        }
    }

    @Override
    public void delete(String fileName) {

        logger.info("Trying do delete the cover with file name {}", fileName);
        // image/small/1231233123124134123.jpeg
        for(ImageSize imageSize : ImageSize.values()){

            String imagePath = imageProperties.getStoragePath()+ imageSize.name().toLowerCase()+"/"+fileName;
            Path path = Paths.get(imagePath);

            try {
                Files.delete(path);
                logger.info("Path for the cover to delete has been found {}", path);
            }catch (NoSuchFileException nse){

                logger.info("Failed to find the cover as a file {}", nse);
                throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Image for this training not found", nse);
            }catch (IOException ioe){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete the image", ioe);

            }
        }
    }
}
