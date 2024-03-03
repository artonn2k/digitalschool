package com.zerogravitysolutions.digitalschool.instructorgroup;

import com.zerogravitysolutions.digitalschool.groups.GroupEntity;
import com.zerogravitysolutions.digitalschool.groups.GroupRepository;
import com.zerogravitysolutions.digitalschool.instructors.InstructorEntity;
import com.zerogravitysolutions.digitalschool.instructors.InstructorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class InstructorGroupServiceImpl implements InstructorGroupService {

    private InstructorRepository instructorRepository;

    private GroupRepository groupRepository;

    private InstructorGroupRepository instructorGroupRepository;

    public InstructorGroupServiceImpl(
            InstructorRepository instructorRepository,
            GroupRepository groupRepository,
            InstructorGroupRepository instructorGroupRepository
    ){
        this.instructorRepository = instructorRepository;
        this.groupRepository = groupRepository;
        this.instructorGroupRepository = instructorGroupRepository;
    }

    @Override
    public InstructorGroupEntity addInstructorToGroup(Long instructorId, Long groupId) {

        GroupEntity groupEntity = groupRepository.findById(groupId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with id "+groupId+ " is not found")
        );

        InstructorEntity instructor = instructorRepository.findById(instructorId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor with id "+instructorId+ " is not found")
        );

        InstructorGroupEntity instructorGroupEntity = new InstructorGroupEntity();
        instructorGroupEntity.setInstructor(instructor);
        instructorGroupEntity.setGroup(groupEntity);


        return instructorGroupRepository.save(instructorGroupEntity);
    }

    @Override
    public Set<InstructorEntity> findInstructorsByGroupId(Long id) {

        GroupEntity groupEntity = groupRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with id "+id+ " is not found")
        );

        Set<InstructorGroupEntity> instructorGroupSet = instructorGroupRepository.findAllByGroup(groupEntity);

        Set<InstructorEntity> instructorSet = new HashSet<>();

        instructorGroupSet.forEach(instructorGroup -> {
            instructorSet.add(instructorGroup.getInstructor());
        });

        return instructorSet ;
    }

    @Override
    public void removeByInstructorIdAndGroupId(Long instructorId, Long groupId) {

        GroupEntity groupEntity = groupRepository.findById(groupId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with id "+groupId+ " is not found")
        );

        InstructorEntity instructor = instructorRepository.findById(instructorId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor with id "+instructorId+ " is not found")
        );

        Optional<InstructorGroupEntity> instructorGroupEntityOptional = instructorGroupRepository.findByInstructorAndGroup(instructor,groupEntity);

        //instructorGroupEntityOptional.ifPresent(instructorGroupRepository::delete);

        if(instructorGroupEntityOptional.isPresent()){

            InstructorGroupEntity instructorGroupEntity = instructorGroupEntityOptional.get();
            instructorGroupRepository.delete(instructorGroupEntity);
        }
    }
}
