package com.zerogravitysolutions.digitalschool.students;

import com.zerogravitysolutions.digitalschool.DTOs.StudentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;


@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity studentEntity){

        StudentEntity createdStudent = studentService.save(studentEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }


    //--------------------------------------------------------------------------------------------------------------------
    @GetMapping(path = "/paged")
    public ResponseEntity<Page<StudentEntity>> getAllStudents(@PageableDefault(size = 10,sort = {"id", "firstName"}) Pageable pageable){

        Page<StudentEntity> studentEntities = studentService.findAll(pageable);

        return ResponseEntity.ok(studentEntities);
    }

    // http://localhost:8085/students?page=0&sort=firstName,desc&sort=id,asc&size=1

/*    @GetMapping(path = "/paged")
    public ResponseEntity<Page<StudentDto>> getAllStudents(@PageableDefault(size = 10, sort = {"firstName", "id"}) Pageable pageable){
        try{
            Page<StudentDto> studentDTOS = studentService.findAll(pageable);
            return ResponseEntity.ok(studentDTOS);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }*/
    //-----------------------------------------------------------------------------------------------------------------------


    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable Long id){

       StudentEntity foundStudent = studentService.findById(id);

        return ResponseEntity.ok(foundStudent);
    }

    @GetMapping(params = "name")
    public ResponseEntity<Set<StudentEntity>> findByNameOrEmail(@RequestParam String name, @RequestParam (required = false) String email){
            Set<StudentEntity> foundStudents = studentService.findByNameOrEmail(name,null);
            return ResponseEntity.ok(foundStudents);
    }



    @PutMapping(path = "/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable Long id, @RequestBody StudentEntity studentEntity){

        if(id == null || studentEntity == null){
            //throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }

//        if(id.equals(studentEntity.getId())){
//
//        }

        //studentEntity.setId(id);
        // in case if you do not send the id in the request body

        return ResponseEntity.ok(studentService.update(id,studentEntity));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<StudentDto> patch(@PathVariable Long id, @RequestBody StudentDto studentDto){

        StudentDto patched = studentService.patchStudent(id, studentDto);

        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(patched);

    }


    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

}
