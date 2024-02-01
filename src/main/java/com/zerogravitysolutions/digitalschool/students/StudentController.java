package com.zerogravitysolutions.digitalschool.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(path = "/students")
    public List<StudentEntity> getStudents(StudentEntity studentEntity){

        return studentService.findAll(studentEntity);
    }

    @GetMapping(path = "/students/{id}")
    public StudentEntity getStudentById(@PathVariable(name = "id") long id){
        return studentService.findById(id);
    }

    @PostMapping(path = "/students")
    public StudentEntity createStudent(@RequestBody StudentEntity studentEntity){

        return studentService.save(studentEntity);
    }

    @DeleteMapping(path = "/students/{id}")
    public void deleteStudentById(@PathVariable(name = "id") long id){
        studentService.deleteStudentById(id);
    }
}
