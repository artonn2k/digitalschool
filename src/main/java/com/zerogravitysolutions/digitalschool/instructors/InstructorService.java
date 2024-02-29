package com.zerogravitysolutions.digitalschool.instructors;

import org.springframework.http.ResponseEntity;

public interface InstructorService {
    InstructorEntity findById(Long id);

    InstructorEntity create(InstructorEntity instructor);

    InstructorEntity update(Long id, InstructorEntity instructor);

    void deleteById(Long id);
}
