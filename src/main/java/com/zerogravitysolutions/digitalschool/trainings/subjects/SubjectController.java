package com.zerogravitysolutions.digitalschool.trainings.subjects;

import com.zerogravitysolutions.digitalschool.DTOs.SubjectDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/trainings/{id}/subjects")
public class SubjectController {

    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> addSubjectToTraining(@PathVariable(name = "id") Long trainingId, @RequestBody @Valid SubjectDTO subject){

        SubjectDTO created = subjectService.addSubjectTotTraining(trainingId,subject);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @GetMapping
    public ResponseEntity<Set<SubjectDTO>> getSubjectsByTrainingId(@PathVariable(name = "id")Long trainingId){

        Set<SubjectDTO> subjectEntities = subjectService.getSubjectsByTrainingId(trainingId);

        return ResponseEntity.ok().body(subjectEntities);
    }

    @PutMapping(path = "/{sid}")
    public ResponseEntity<SubjectDTO> update(@PathVariable(name = "sid") Long subjectId, @RequestBody SubjectDTO subject){

        SubjectDTO updated = subjectService.update(subjectId,subject);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(path = "/{sid}")
    public ResponseEntity<Void> delete(@PathVariable(name = "sid") Long subjectId){
        subjectService.delete(subjectId);
        return ResponseEntity.noContent().build();
    }


}
