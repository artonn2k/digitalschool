package com.zerogravitysolutions.digitalschool.configs;

import com.zerogravitysolutions.digitalschool.imagestorage.ImageSize;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.images")
public class ImageProperties {

    private String storagePath;
    private int smallWidth;
    private int smallHeight;
    private int mediumWidth;
    private int mediumHeight;
    private int largeWidth;
    private int largeHeight;

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public int getSmallWidth() {
        return smallWidth;
    }

    public void setSmallWidth(int smallWidth) {
        this.smallWidth = smallWidth;
    }

    public int getSmallHeight() {
        return smallHeight;
    }

    public void setSmallHeight(int smallHeight) {
        this.smallHeight = smallHeight;
    }

    public int getMediumWidth() {
        return mediumWidth;
    }

    public void setMediumWidth(int mediumWidth) {
        this.mediumWidth = mediumWidth;
    }

    public int getMediumHeight() {
        return mediumHeight;
    }

    public void setMediumHeight(int mediumHeight) {
        this.mediumHeight = mediumHeight;
    }

    public int getLargeWidth() {
        return largeWidth;
    }

    public void setLargeWidth(int largeWidth) {
        this.largeWidth = largeWidth;
    }

    public int getLargeHeight() {
        return largeHeight;
    }

    public void setLargeHeight(int largeHeight) {
        this.largeHeight = largeHeight;
    }

    public int getTargetWidth(ImageSize imageSize){

        return switch(imageSize){

            case SMALL -> smallWidth;
            case MEDIUM -> mediumWidth;
            case LARGE -> largeWidth;
            default -> 0;
        };
    }

    public int getTargetHeight(ImageSize imageSize){

        return switch (imageSize){

            case SMALL -> smallHeight;
            case MEDIUM -> mediumHeight;
            case LARGE -> largeHeight;
            default -> 0;
        };
    }
}
