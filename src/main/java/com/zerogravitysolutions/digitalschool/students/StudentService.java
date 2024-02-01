package com.zerogravitysolutions.digitalschool.students;

import java.util.List;

public interface StudentService {

    StudentEntity findById(long id);
    StudentEntity save(StudentEntity studentEntity);

    List<StudentEntity> findAll(StudentEntity studentEntity);

    void deleteStudentById(long id);

    StudentEntity update(long id, StudentEntity updatedStudent);
}
