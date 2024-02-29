package com.zerogravitysolutions.digitalschool.instructors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class InstructorServiceImpl implements InstructorService {

    private InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public InstructorEntity create(InstructorEntity instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public InstructorEntity update(Long id, InstructorEntity instructor) {

        instructorRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor with id " + id + " is not found"));


        return instructorRepository.save(instructor);
    }

    @Override
    public InstructorEntity findById(Long id) {
        InstructorEntity instructorEntity = instructorRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor with id " + id + " is not found"));

        return instructorEntity;
    }

    @Override
    public void deleteById(Long id) {

        InstructorEntity instructorEntity = instructorRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor with id " + id + " is not found"));

        instructorRepository.delete(instructorEntity);
    }


}
