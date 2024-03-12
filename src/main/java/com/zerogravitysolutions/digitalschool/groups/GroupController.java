package com.zerogravitysolutions.digitalschool.groups;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/groups")
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GroupEntity> findById(@PathVariable Long id){

        GroupEntity groupFound = groupService.findById(id);

        return ResponseEntity.ok(groupFound);
    }

    @PostMapping
    public ResponseEntity<GroupEntity> create(@RequestBody GroupEntity group){

        GroupEntity created = groupService.create(group);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        groupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
