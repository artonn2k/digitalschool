package com.zerogravitysolutions.digitalschool.students;


import com.zerogravitysolutions.digitalschool.DTOs.GroupDTO;
import com.zerogravitysolutions.digitalschool.DTOs.StudentDTO;
import com.zerogravitysolutions.digitalschool.groups.GroupEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface StudentService {


    StudentDTO findById(Long id);

    StudentEntity save(StudentEntity studentEntity);

    void deleteStudentById(Long id);

    StudentDTO update(Long id, StudentDTO student);

    StudentDTO patchStudent(Long id, StudentDTO studentDto);

    Page<StudentDTO> findAll(Pageable pageable);

    Set<StudentDTO> findByNameOrEmail(String name, String email);

    List<StudentDTO> searchStudents(String keyword);

    Set<GroupDTO> getGroupsByStudentId(Long id);

    void addStudentToGroup(Long studentId, Long groupId);

    Set<StudentDTO> getStudentsByGroupId(Long id);

    void removeStudentFromGroup(Long studentId, Long groupId);

}
