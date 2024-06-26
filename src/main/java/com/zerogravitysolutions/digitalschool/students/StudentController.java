package com.zerogravitysolutions.digitalschool.students;

import com.zerogravitysolutions.digitalschool.DTOs.StudentDTO;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;


@RestController
//@PreAuthorize("hasAnyRole('STUDENT', 'ADMINISTRATOR')")
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity studentEntity) {

        StudentEntity createdStudent = studentService.save(studentEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }


    //--------------------------------------------------------------------------------------------------------------------
    @GetMapping(path = "/paged")
    public ResponseEntity<Page<StudentDTO>> getAllStudents(@PageableDefault(size = 10, sort = {"id", "firstName"}) Pageable pageable) {

        Page<StudentDTO> studentDTOS = studentService.findAll(pageable);

        return ResponseEntity.ok(studentDTOS);
    }

    // http://localhost:8085/students?page=0&sort=firstName,desc&sort=id,asc&size=1

    //-----------------------------------------------------------------------------------------------------------------------


    @GetMapping(path = "/{id}")
    @RolesAllowed("STUDENT")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        StudentDTO foundStudent = studentService.findById(id);

        return ResponseEntity.ok(foundStudent);
    }

    @GetMapping(params = "name")
    public ResponseEntity<Set<StudentDTO>> findByNameOrEmail(@RequestParam String name, @RequestParam(required = false) String email) {
        Set<StudentDTO> foundStudents = studentService.findByNameOrEmail(name, null);
        return ResponseEntity.ok(foundStudents);
    }

    @GetMapping(params = "search")
    public ResponseEntity<List<StudentDTO>> findBySearch(@RequestParam("search") String keyword) {
        List<StudentDTO> studentsSearched = studentService.searchStudents(keyword);

        return ResponseEntity.ok(studentsSearched);
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {

        if (id == null || studentDTO == null) {
            //throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }

//        if(id.equals(studentEntity.getId())){
//
//        }

        //studentEntity.setId(id);
        // in case if you do not send the id in the request body

        return ResponseEntity.ok(studentService.update(id, studentDTO));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<StudentDTO> patch(@PathVariable Long id, @RequestBody StudentDTO studentDto) {

        StudentDTO patched = studentService.patchStudent(id, studentDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(patched);

    }


    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/{id}/image")
    public ResponseEntity<Void> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile image) {

        studentService.uploadImage(id, image);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(path = "/{id}/image")
    public ResponseEntity<ByteArrayResource> readImage(@PathVariable Long id) {

        ByteArrayResource profilePicture = studentService.readImage(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "student-" + id + "-image.jpeg" + "\"")
                .body(profilePicture);
    }

}
