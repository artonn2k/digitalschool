package com.zerogravitysolutions.digitalschool.DTOs;

import com.zerogravitysolutions.digitalschool.commons.BaseDto;
import jakarta.validation.constraints.NotBlank;

public class SubjectDTO extends BaseDto {

    @NotBlank(message = "Title must be provided")
    private String title;
    private String description;
    private String icon;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
