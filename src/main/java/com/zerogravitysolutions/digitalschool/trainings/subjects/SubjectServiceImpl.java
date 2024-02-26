package com.zerogravitysolutions.digitalschool.trainings.subjects;

import com.zerogravitysolutions.digitalschool.DTOs.SubjectDTO;
import com.zerogravitysolutions.digitalschool.trainings.TrainingEntity;
import com.zerogravitysolutions.digitalschool.trainings.TrainingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository subjectRepository;
    private TrainingRepository trainingRepository;

    private SubjectMapper subjectMapper;


    public SubjectServiceImpl(SubjectRepository subjectRepository, TrainingRepository trainingRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.trainingRepository = trainingRepository;
        this.subjectMapper = subjectMapper;
    }


    @Override
    public SubjectDTO addSubjectTotTraining(Long trainingId, SubjectDTO subject) {

        TrainingEntity trainingEntity = trainingRepository.findById(trainingId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Training with id " + trainingId + " not found")
                );

        SubjectEntity subjectEntity = subjectMapper.mapDtoToEntity(subject);

        subjectEntity.setTraining(trainingEntity);

        SubjectEntity saved = subjectRepository.save(subjectEntity);
        return subjectMapper.mapEntityToDto(saved);
    }

    @Override
    public Set<SubjectDTO> getSubjectsByTrainingId(Long trainingId) {

        TrainingEntity trainingEntity = trainingRepository.findById(trainingId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Training with id " + trainingId + " not found")
                );

        return subjectMapper.mapEntitiesToDtos(trainingEntity.getSubjects());
    }

    @Override
    public SubjectDTO update(Long subjectId, SubjectDTO subject) {

        SubjectEntity subjectEntity = subjectRepository.findById(subjectId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Subject with id " + subjectId + " not found")
                );

        SubjectEntity merged = subjectMapper.mapDtoToEntity(subject);
        SubjectEntity updated = subjectRepository.save(merged);

        return subjectMapper.mapEntityToDto(updated);
    }

    @Override
    public void delete(Long subjectId) {

        SubjectEntity subjectEntity = subjectRepository.findById(subjectId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Subject with id " + subjectId + " not found")
                );

        subjectRepository.delete(subjectEntity);
    }
}
