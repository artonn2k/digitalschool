package com.zerogravitysolutions.digitalschool.instructors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/instructors")
public class InstructorController {

    private InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public ResponseEntity<InstructorEntity> create(@RequestBody InstructorEntity instructor){
        return ResponseEntity.status(HttpStatus.CREATED).body(instructorService.create(instructor));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<InstructorEntity> getById(@PathVariable Long id){
        return ResponseEntity.ok(instructorService.findById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<InstructorEntity> update(@PathVariable Long id, @RequestBody InstructorEntity instructor){
        if (id == null || instructor == null) {
            //throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }

        if(id.equals(instructor.getId())){

        }

        instructor.setId(id);
        // in case if you do not send the id in the request body
        return ResponseEntity.ok(instructorService.update(id,instructor));
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
         instructorService.deleteById(id);
         return ResponseEntity.noContent().build();
    }
}
