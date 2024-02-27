package com.zerogravitysolutions.digitalschool.DTOs;

import com.zerogravitysolutions.digitalschool.commons.BaseDto;
import jakarta.validation.constraints.NotBlank;

public class GroupDTO extends BaseDto {

    @NotBlank
    private String title;

    private String description;

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
}
