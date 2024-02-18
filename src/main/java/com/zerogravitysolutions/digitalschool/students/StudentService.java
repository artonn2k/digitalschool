package com.zerogravitysolutions.digitalschool.students;

import com.zerogravitysolutions.digitalschool.DTOs.StudentDTO;



import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface StudentService {


    StudentEntity findById(Long id);
    StudentEntity save(StudentEntity studentEntity);

    //List<StudentEntity> getStudentList(StudentEntity studentEntity);

    void deleteStudentById(Long id);

    StudentEntity update(Long id, StudentEntity student);


   //Page<StudentDTO> findAll(Pageable pageable);
    Page<StudentEntity> findAll(Pageable pageable);
    Set<StudentEntity> findByNameOrEmail(String name, String email);
}
