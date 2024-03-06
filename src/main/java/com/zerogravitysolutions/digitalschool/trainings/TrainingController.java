package com.zerogravitysolutions.digitalschool.trainings;

import com.zerogravitysolutions.digitalschool.DTOs.TrainingDTO;
import com.zerogravitysolutions.digitalschool.imagestorage.ImageSize;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/trainings")
public class TrainingController {

    private TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }


    @PostMapping
    public ResponseEntity<TrainingEntity> save(@RequestBody TrainingEntity trainingEntity) {
        TrainingEntity saved = trainingService.save(trainingEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TrainingEntity> findById(@PathVariable Long id) {
        TrainingEntity foundTraining = trainingService.findById(id);
        return ResponseEntity.ok(foundTraining);
    }

    @GetMapping
    public ResponseEntity<List<TrainingEntity>> findAll() {

        List<TrainingEntity> trainings = trainingService.findAll();

        return ResponseEntity.ok(trainings);
    }

    @GetMapping(params = "search")
    public ResponseEntity<List<TrainingEntity>> search(@RequestParam("search") String keyword) {
        List<TrainingEntity> searchedTraining = trainingService.searchTraining(keyword);
        return ResponseEntity.ok(searchedTraining);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<TrainingEntity> update(@PathVariable Long id, @RequestBody TrainingEntity trainingEntity) {
        if (id == null || trainingEntity == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(trainingService.update(id, trainingEntity));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<TrainingDTO> patch(@PathVariable Long id, @RequestBody TrainingDTO trainingDto) {
        TrainingDTO patched = trainingService.patchTraining(id, trainingDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(patched);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trainingService.deleteTrainingById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/{id}/cover")
    public ResponseEntity<TrainingEntity> uploadCoverImage(@PathVariable Long id, @RequestParam("file") MultipartFile cover) {

        return ResponseEntity.ok(trainingService.uploadCover(id, cover));
    }

    @GetMapping(path = "/{id}/cover")
    public ResponseEntity<Resource> readCover(@PathVariable Long id, @RequestParam ImageSize size) {

        Resource resource = trainingService.readCover(id, size);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }

    @DeleteMapping(path = "/{id}/cover")
    public ResponseEntity<TrainingEntity> deleteCover(@PathVariable Long id){

        TrainingEntity trainingEntity = trainingService.deleteCover(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
