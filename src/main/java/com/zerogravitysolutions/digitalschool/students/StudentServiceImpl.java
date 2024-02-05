package com.zerogravitysolutions.digitalschool.students;

import com.zerogravitysolutions.digitalschool.DTOs.StudentDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
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
    public StudentEntity findById(long id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Student with this id is not found"
                ));

        return studentEntity;
    }

    //------------------------------------------------------------------------------------------------------

    @Override
    public List<StudentEntity> findAll() {
        return studentRepository.findAll();
    }
    //------------------------------------------------------------------------------------------------------

    @Override
    public StudentEntity update(@PathVariable(name = "id") long id, StudentEntity updatedStudent) {
        StudentEntity existingStudent = studentRepository.findById(id).orElse(null);

        if(existingStudent !=null){
            existingStudent.setFirstName(updatedStudent.getFirstName());
            existingStudent.setLastName(updatedStudent.getLastName());
            existingStudent.setProfilePicture(updatedStudent.getProfilePicture());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setPhoneNumber(updatedStudent.getPhoneNumber());
            existingStudent.setAddress(updatedStudent.getAddress());

            return studentRepository.save(existingStudent);

        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Student with id " +id +" not found");
        }

    }

    //------------------------------------------------------------------------------------------------------
    @Override
    public Set<StudentEntity> findByNameOrEmail(String name, String email) {
        return studentRepository.findByFirstNameIgnoreCase(name);
    }

    //------------------------------------------------------------------------------------------------------

    @Override
    public void deleteStudentById(long id) {
         studentRepository.deleteById(id);
    }
    //------------------------------------------------------------------------------------------------------

    @Override
    public Page<StudentDTO> findAll(Pageable pageable) {
        Page<StudentEntity> studentEntityPage = studentRepository.findAll(pageable);
            return  studentEntityPage.map(student->modelMapper.map(student,StudentDTO.class));
    }

    public StudentDTO convertToDTO(StudentEntity studentEntity) {
        return modelMapper.map(studentEntity, StudentDTO.class);
    }
    //------------------------------------------------------------------------------------------------------

    public List<StudentEntity> allStudents(){
        return studentRepository.findAll();
    }
}
