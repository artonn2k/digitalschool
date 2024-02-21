package com.zerogravitysolutions.digitalschool.trainings.subjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zerogravitysolutions.digitalschool.commons.BaseEntity;
import com.zerogravitysolutions.digitalschool.trainings.TrainingEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "subjects")
public class SubjectEntity extends BaseEntity {

    private String title;
    private String description;
    private String icon;

    @ManyToOne
    @JoinColumn(name = "training_id")
    @JsonIgnoreProperties("subjects")
    private TrainingEntity training;

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

    public TrainingEntity getTrainingEntity() {
        return training;
    }

    public void setTrainingEntity(TrainingEntity training) {
        this.training = training;
    }
}
