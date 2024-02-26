package com.zerogravitysolutions.digitalschool.trainings;

import com.zerogravitysolutions.digitalschool.DTOs.TrainingDTO;
import com.zerogravitysolutions.digitalschool.trainings.commons.TrainingMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    private TrainingRepository trainingRepository;

    private TrainingMapper trainingMapper;

    public TrainingServiceImpl(TrainingRepository trainingRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }


    @Override
    public TrainingEntity save(TrainingEntity trainingEntity) {
        return trainingRepository.save(trainingEntity);
    }

    @Override
    public TrainingEntity findById(Long id) {
        return trainingRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Training with id " + id + " not found")
                );
    }

    @Override
    public List<TrainingEntity> findAll() {
        return trainingRepository.findAll();
    }

    @Override
    public List<TrainingEntity> searchTraining(String keyword) {
        return trainingRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    @Override
    public TrainingEntity update(Long id, TrainingEntity training) {
        trainingRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Training with this id is not found"
                ));
        return trainingRepository.save(training);
    }

    @Override
    public TrainingDTO patchTraining(Long id, TrainingDTO trainingDto) {

        TrainingEntity trainingEntity = this.findById(id);

        trainingMapper.mapDtoToEntity(trainingDto, trainingEntity);

        TrainingEntity patchedTraining = trainingRepository.save(trainingEntity);

        return trainingMapper.mapEntityToDto(patchedTraining);
    }

    @Override
    public void deleteTrainingById(Long id) {
        trainingRepository.deleteById(id);
    }

}
