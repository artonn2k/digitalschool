package com.zerogravitysolutions.digitalschool.groups;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity,Long> {

    Set<GroupEntity> findAllByInstructorGroupSetInstructorId(Long id);
}
