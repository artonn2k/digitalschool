package com.zerogravitysolutions.digitalschool.students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    Set<StudentEntity> findByFirstNameOrEmailIgnoreCase(String firstName, String email);

    List<StudentEntity> findByFirstNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String firstNameKeyword, String emailKeyword);

    Optional<StudentEntity> findByEmailIgnoreCase(String email);
}
