package com.zerogravitysolutions.digitalschool.students;

import com.zerogravitysolutions.digitalschool.DTOs.StudentDto;
import com.zerogravitysolutions.digitalschool.students.commons.StudentMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentEntity save(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }



    //------------------------------------------------------------------------------------------------------
    @Override
    public StudentEntity findById(Long id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Student with this id is not found"
                ));

        return studentEntity;
    }

    //------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------

    @Override
    public StudentEntity update(Long id, StudentEntity studentEntity) {

        studentRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Student with this id is not found"
                ));;

        return studentRepository.save(studentEntity);
    }

    @Override
    public StudentDto patchStudent(Long id, StudentDto studentDto) {

        StudentEntity studentEntity =this.findById(id);

        StudentMapper.mapDtoToEntity(studentDto, studentEntity);

        StudentEntity patched = studentRepository.save(studentEntity);

        return StudentMapper.mapEntitytoDto(patched);
    }


    //------------------------------------------------------------------------------------------------------
    @Override
    public Set<StudentEntity> findByNameOrEmail(String name, String email) {
        return studentRepository.findByFirstNameOrEmailIgnoreCase(name,email);

    }

    @Override
    public List<StudentEntity> searchStudents(String keyword) {
        return studentRepository.findByFirstNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword,keyword);
    }

    //------------------------------------------------------------------------------------------------------

    @Override
    public void deleteStudentById(Long id) {
         studentRepository.deleteById(id);
    }


    //------------------------------------------------------------------------------------------------------


    //returning pageable list of students
    @Override
    public Page<StudentEntity> findAll(Pageable pageable) {

        return studentRepository.findAll(pageable);

    }

    //this one returns the lists of pageable StudentDTOs
/*   @Override
    public Page<StudentDto> findAll(Pageable pageable) {
        Page<StudentEntity> studentEntityPage = studentRepository.findAll(pageable);
            return  studentEntityPage.map(student->modelMapper.map(student,StudentDto.class));
    }

    public StudentDto convertToDTO(StudentEntity studentEntity) {
        return modelMapper.map(studentEntity, StudentDto.class);
    }*/
    //------------------------------------------------------------------------------------------------------

}
