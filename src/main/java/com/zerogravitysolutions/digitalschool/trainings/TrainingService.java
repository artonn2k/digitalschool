package com.zerogravitysolutions.digitalschool.trainings;

import com.zerogravitysolutions.digitalschool.DTOs.TrainingDto;

import java.util.List;
import java.util.Set;

public interface TrainingService {

    TrainingEntity save(TrainingEntity trainingEntity);
    TrainingEntity findById(Long id);

    List<TrainingEntity> findAll();

    List<TrainingEntity> searchTraining(String keyword);

    TrainingEntity update(Long id, TrainingEntity training);

    TrainingDto patchTraining(Long id, TrainingDto trainingDto);

    void deleteTrainingById(Long id);

}
