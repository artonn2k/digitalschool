package com.zerogravitysolutions.digitalschool.students;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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


    //------------------------------------------------------------------------------------------------------
    @Override
    public Set<StudentEntity> findByNameOrEmail(String name, String email) {
        return studentRepository.findByFirstNameOrEmailIgnoreCase(name,email);

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
    public Page<StudentDTO> findAll(Pageable pageable) {
        Page<StudentEntity> studentEntityPage = studentRepository.findAll(pageable);
            return  studentEntityPage.map(student->modelMapper.map(student,StudentDTO.class));
    }

    public StudentDTO convertToDTO(StudentEntity studentEntity) {
        return modelMapper.map(studentEntity, StudentDTO.class);
    }*/
    //------------------------------------------------------------------------------------------------------

}
