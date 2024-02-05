package com.zerogravitysolutions.digitalschool.students;

import com.zerogravitysolutions.digitalschool.DTOs.StudentDTO;
//import org.hibernate.query.Page;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    StudentEntity findById(long id);
    StudentEntity save(StudentEntity studentEntity);

    List<StudentEntity> findAll();

    void deleteStudentById(long id);

    StudentEntity update(long id, StudentEntity updatedStudent);

    Page<StudentDTO> findAll(Pageable pageable);
}
