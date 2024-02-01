package com.zerogravitysolutions.digitalschool.students;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentEntity findById(long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public StudentEntity save(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }

    @Override
    public List<StudentEntity> findAll(StudentEntity studentEntity) {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudentById(long id) {
         studentRepository.deleteById(id);
    }

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

    public List<StudentEntity> allStudents(){
        return studentRepository.findAll();
    }
}
