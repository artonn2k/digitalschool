package com.zerogravitysolutions.digitalschool.instructorgroup;

import com.zerogravitysolutions.digitalschool.groups.GroupEntity;
import com.zerogravitysolutions.digitalschool.instructors.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface InstructorGroupRepository extends JpaRepository<InstructorGroupEntity, Long> {

    List<InstructorGroupEntity> findByInstructor(InstructorEntity instructor);

    Set<InstructorGroupEntity> findAllByGroup(GroupEntity groupEntity);


    Optional<InstructorGroupEntity> findByInstructorAndGroup(InstructorEntity instructor, GroupEntity group);
}
