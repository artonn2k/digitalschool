package com.zerogravitysolutions.digitalschool.students;

import com.zerogravitysolutions.digitalschool.DTOs.StudentDTO;
import com.zerogravitysolutions.digitalschool.students.commons.StudentMapperMapStruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    private StudentMapperMapStruct studentMapperMapStruct;

    public StudentServiceImpl(StudentRepository studentRepository,
                              StudentMapperMapStruct studentMapperMapStruct) {
        this.studentRepository = studentRepository;;
        this.studentMapperMapStruct = studentMapperMapStruct;
    }

    @Override
    public StudentEntity save(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }


    //------------------------------------------------------------------------------------------------------
    @Override
    public StudentDTO findById(Long id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student with this id is not found"
                ));

        return studentMapperMapStruct.mapEntityToDto(studentEntity);
    }

    //------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------

    @Override
    public StudentDTO update(Long id, StudentDTO studentDTO) {

        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student with this id is not found"
                ));

        StudentEntity merged = studentMapperMapStruct.mapDtoToEntity(studentDTO);
        StudentEntity updated = studentRepository.save(merged);

        return studentMapperMapStruct.mapEntityToDto(updated);
    }

    @Override
    public StudentDTO patchStudent(Long id, StudentDTO studentDto) {

        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Student with this id "+id+" is not found")
                );

        //StudentMapper.mapDtoToEntity(studentDto, studentEntity);
        studentMapperMapStruct.mapDtoToEntity(studentDto, studentEntity);

        StudentEntity patched = studentRepository.save(studentEntity);

        //return StudentMapper.mapEntityToDto(patched);
        return studentMapperMapStruct.mapEntityToDto(patched);
    }


    //------------------------------------------------------------------------------------------------------
    @Override
    public Set<StudentDTO> findByNameOrEmail(String name, String email) {
        Set<StudentEntity> foundStudents = studentRepository.findByFirstNameOrEmailIgnoreCase(name, email);

        return studentMapperMapStruct.mapEntitiesToDtos(foundStudents);

    }

    @Override
    public List<StudentEntity> searchStudents(String keyword) {
        return studentRepository.findByFirstNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
    }

    //------------------------------------------------------------------------------------------------------

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }


    //------------------------------------------------------------------------------------------------------


    //returning pageable list of students
    @Override
    public Page<StudentDTO> findAll(Pageable pageable) {
        Page<StudentEntity> studentEntities = studentRepository.findAll(pageable);
        return studentEntities.map(studentMapperMapStruct::mapEntityToDto);

    }

    //------------------------------------------------------------------------------------------------------

}
