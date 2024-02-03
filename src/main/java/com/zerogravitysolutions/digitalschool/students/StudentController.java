package com.zerogravitysolutions.digitalschool.students;

import com.zerogravitysolutions.digitalschool.DTOs.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentEntity> getStudents(StudentEntity studentEntity){

        return studentService.findAll(studentEntity);
    }

    @GetMapping(path = "/{id}")
    public StudentEntity getStudentById(@PathVariable long id){
        return studentService.findById(id);
    }

    @PostMapping
    public StudentEntity createStudent(@RequestBody StudentEntity studentEntity){

        return studentService.save(studentEntity);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<StudentEntity> updatedStudent(@PathVariable long id,@RequestBody StudentEntity updatedStudent){
        StudentEntity updatedEntity = studentService.update(id,updatedStudent);
        if(updatedEntity!=null){
            return ResponseEntity.ok(updatedEntity);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudentById(@PathVariable long id){
        studentService.deleteStudentById(id);
    }

    @GetMapping(path = "/paged")
    public ResponseEntity<Page<StudentDTO>> getAllStudents(@PageableDefault(size = 10, sort = "id") Pageable pageable){
        try{
            Page<StudentDTO> studentDTOS = studentService.findAll(pageable);
            return ResponseEntity.ok(studentDTOS);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
