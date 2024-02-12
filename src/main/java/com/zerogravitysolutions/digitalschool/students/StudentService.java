package com.zerogravitysolutions.digitalschool.students;

import com.zerogravitysolutions.digitalschool.DTOs.StudentDTO;



import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface StudentService {

    StudentEntity findById(long id);
    StudentEntity save(StudentEntity studentEntity);

    void deleteStudentById(long id);

    StudentEntity update(long id, StudentEntity updatedStudent);

   //Page<StudentDTO> findAll(Pageable pageable);
   Page<StudentEntity> findAll(Pageable pageable);
    Set<StudentEntity> findByNameOrEmail(String name, String email);
}
