package com.zerogravitysolutions.digitalschool.trainings.subjects;

import com.zerogravitysolutions.digitalschool.DTOs.SubjectDTO;

import java.util.Set;

public interface SubjectService {

    SubjectDTO addSubjectTotTraining(Long trainingId, SubjectDTO subject);

    Set<SubjectDTO> getSubjectsByTrainingId(Long trainingId);

    SubjectDTO update(Long subjectId, SubjectDTO subject);

    void delete(Long subjectId);
}

