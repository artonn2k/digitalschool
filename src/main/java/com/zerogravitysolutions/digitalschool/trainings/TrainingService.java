package com.zerogravitysolutions.digitalschool.trainings;

import com.zerogravitysolutions.digitalschool.DTOs.TrainingDTO;
import com.zerogravitysolutions.digitalschool.imagestorage.ImageSize;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TrainingService {

    TrainingEntity save(TrainingEntity trainingEntity);

    TrainingEntity findById(Long id);

    List<TrainingEntity> findAll();

    List<TrainingEntity> searchTraining(String keyword);

    TrainingEntity update(Long id, TrainingEntity training);

    TrainingDTO patchTraining(Long id, TrainingDTO trainingDto);

    void deleteTrainingById(Long id);

    TrainingEntity uploadCover(Long id, MultipartFile cover);

    Resource readCover(Long id, ImageSize size);

    TrainingEntity deleteCover(Long id);
}
