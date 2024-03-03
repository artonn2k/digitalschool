package com.zerogravitysolutions.digitalschool.instructorgroup;

import com.zerogravitysolutions.digitalschool.instructors.InstructorEntity;

import java.util.Set;

public interface InstructorGroupService {
    InstructorGroupEntity addInstructorToGroup(Long instructorId, Long groupId);

    Set<InstructorEntity> findInstructorsByGroupId(Long id);

    void removeByInstructorIdAndGroupId(Long instructorId, Long groupId);
}
