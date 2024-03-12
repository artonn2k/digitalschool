package com.zerogravitysolutions.digitalschool.groups;

import com.zerogravitysolutions.digitalschool.exceptions.GroupNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GroupServiceImpl implements GroupService{

    private GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public GroupEntity findById(Long id) {

        GroupEntity groupEntity = groupRepository.findById(id).orElseThrow(
                () -> new GroupNotFoundException("Group with this id " +id+ " is not found"));

        return groupEntity;
    }

    @Override
    public GroupEntity create(GroupEntity group) {

        return groupRepository.save(group);
    }

    @Override
    public void deleteById(Long id) {

        GroupEntity groupEntity = groupRepository.findById(id).orElseThrow(
                () -> new GroupNotFoundException("Group with this id " +id+ " is not found"));

        groupRepository.delete(groupEntity);
    }
}
