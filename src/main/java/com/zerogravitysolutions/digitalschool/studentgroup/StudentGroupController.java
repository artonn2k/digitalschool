package com.zerogravitysolutions.digitalschool.studentgroup;

import com.zerogravitysolutions.digitalschool.DTOs.GroupDTO;
import com.zerogravitysolutions.digitalschool.DTOs.StudentDTO;
import com.zerogravitysolutions.digitalschool.groups.GroupEntity;
import com.zerogravitysolutions.digitalschool.groups.GroupService;
import com.zerogravitysolutions.digitalschool.students.StudentEntity;
import com.zerogravitysolutions.digitalschool.students.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class StudentGroupController {

    private StudentService studentService;
    private GroupService groupService;

    public StudentGroupController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping(path = "/students/{id}/groups")
    public ResponseEntity<Set<GroupDTO>> getGroupsByStudentId(@PathVariable Long id) {
        Set<GroupDTO> groups = studentService.getGroupsByStudentId(id);

        return ResponseEntity.ok(groups);
    }

    @PostMapping(path = "/students/{sid}/groups/{gid}")
    public ResponseEntity<Void> addStudentToGroup(@PathVariable(name = "sid")Long studentId, @PathVariable(name = "gid")Long groupId){

        studentService.addStudentToGroup(studentId,groupId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping(path = "/groups/{id}/students")
    public ResponseEntity<Set<StudentDTO>> getStudentsByGroupId(@PathVariable Long id) {
        Set<StudentDTO> groups = studentService.getStudentsByGroupId(id);


        return ResponseEntity.ok(groups);
    }

    @DeleteMapping(path = "/students/{sid}/groups/{gid}")
    public ResponseEntity<Void> removeStudentFromGroup(@PathVariable(name = "sid")Long studentId, @PathVariable(name = "gid")Long groupId){
        studentService.removeStudentFromGroup(studentId,groupId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /*
     *
     * GET /students/{sid}/groups -- DONE
     * GET /groups/{id}/students -- DONE
     * POST /students/{sid}/groups/{gid} -- DONE
     * DELETE /students/{sid}/groups/{gid} -- DONE
     *
     * */
}
