package com.zerogravitysolutions.digitalschool.trainings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingEntity, Long> {
    List<TrainingEntity> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String titleKeyword,String descriptionKeyword);
}
