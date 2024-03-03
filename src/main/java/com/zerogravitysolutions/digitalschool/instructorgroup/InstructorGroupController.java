package com.zerogravitysolutions.digitalschool.instructorgroup;

import com.zerogravitysolutions.digitalschool.groups.GroupEntity;
import com.zerogravitysolutions.digitalschool.groups.GroupService;
import com.zerogravitysolutions.digitalschool.instructors.InstructorEntity;
import com.zerogravitysolutions.digitalschool.instructors.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class InstructorGroupController {

    private InstructorService instructorService;

    private GroupService groupService;

    private InstructorGroupService instructorGroupService;

    public InstructorGroupController(
            InstructorService instructorService,
            GroupService groupService,
            InstructorGroupService instructorGroupService
    ) {
        this.instructorService = instructorService;
        this.groupService = groupService;
        this.instructorGroupService = instructorGroupService;
    }

    @PostMapping(path = "/instructors/{instructorId}/groups/{gid}")
    public ResponseEntity<InstructorGroupEntity> addInstructorToGroup(@PathVariable(name = "instructorId") Long instructorId, @PathVariable(name = "gid") Long groupId){

        return ResponseEntity.status(HttpStatus.CREATED).body(instructorGroupService.addInstructorToGroup(instructorId,groupId));
    }

    @GetMapping(path = "/instructors/{id}/groups")
    public ResponseEntity<Set<GroupEntity>> findGroupsByInstructorId(@PathVariable Long id){

        return ResponseEntity.ok(instructorService.findGroupsByInstructorId(id));
    }

    @GetMapping(path = "/groups/{id}/instructors")
    public ResponseEntity<Set<InstructorEntity>> findInstructorsByGroupId(@PathVariable Long id){
        return ResponseEntity.ok(instructorGroupService.findInstructorsByGroupId(id));
    }

    @DeleteMapping(path = "/instructors/{instructorId}/groups/{gid}")
    public ResponseEntity<Void> removeInstructorFromGroup(@PathVariable(name = "instructorId") Long instructorId, @PathVariable(name = "gid") Long groupId){

        instructorGroupService.removeByInstructorIdAndGroupId(instructorId,groupId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    /*
     *
     * GET /instructors/{id}/groups -- DONE
     * GET /groups/{id}/instructors --- DONE
     * POST /instructors/{id}/groups/{gid} -- DONE
     * DELETE /instructors/{id}/groups/{gid}
     *
     * */
}
