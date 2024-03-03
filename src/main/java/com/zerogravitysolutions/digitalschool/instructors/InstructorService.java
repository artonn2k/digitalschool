package com.zerogravitysolutions.digitalschool.instructors;

import com.zerogravitysolutions.digitalschool.groups.GroupEntity;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface InstructorService {
    InstructorEntity findById(Long id);

    InstructorEntity create(InstructorEntity instructor);

    InstructorEntity update(Long id, InstructorEntity instructor);

    void deleteById(Long id);

    Set<GroupEntity> findGroupsByInstructorId(Long id);
}
