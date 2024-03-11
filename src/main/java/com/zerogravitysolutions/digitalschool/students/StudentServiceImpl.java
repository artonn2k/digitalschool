package com.zerogravitysolutions.digitalschool.students;

import com.zerogravitysolutions.digitalschool.DTOs.GroupDTO;
import com.zerogravitysolutions.digitalschool.DTOs.StudentDTO;
import com.zerogravitysolutions.digitalschool.exceptions.StudentNotFoundException;
import com.zerogravitysolutions.digitalschool.groups.GroupEntity;
import com.zerogravitysolutions.digitalschool.groups.GroupRepository;
import com.zerogravitysolutions.digitalschool.groups.commons.GroupMapper;
import com.zerogravitysolutions.digitalschool.students.commons.StudentMapperMapStruct;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    private StudentMapperMapStruct studentMapperMapStruct;
    private GroupMapper groupMapper;

    private GroupRepository groupRepository;

    public StudentServiceImpl(StudentRepository studentRepository,
                              StudentMapperMapStruct studentMapperMapStruct,
                              GroupRepository groupRepository, GroupMapper groupMapper) {
        this.studentRepository = studentRepository;
        this.studentMapperMapStruct = studentMapperMapStruct;
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.groupRepository = groupRepository;

    }

    @Override
    public StudentEntity save(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }

    @Override
    public StudentDTO findById(Long id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException("Student with this id is not found"));

        return studentMapperMapStruct.mapEntityToDto(studentEntity);
    }

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
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with this id " + id + " is not found")
                );

        //StudentMapper.mapDtoToEntity(studentDto, studentEntity);
        studentMapperMapStruct.mapDtoToEntity(studentDto, studentEntity);

        StudentEntity patched = studentRepository.save(studentEntity);

        //return StudentMapper.mapEntityToDto(patched);
        return studentMapperMapStruct.mapEntityToDto(patched);
    }

    @Override
    public Set<StudentDTO> findByNameOrEmail(String name, String email) {
        Set<StudentEntity> foundStudents = studentRepository.findByFirstNameOrEmailIgnoreCase(name, email);

        return studentMapperMapStruct.mapEntitiesToDtos(foundStudents);

    }

    @Override
    public List<StudentDTO> searchStudents(String keyword) {
        return studentMapperMapStruct.mapEntitiesToDtos(
                studentRepository.findByFirstNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword)
        );
    }

    @Override
    public Set<GroupDTO> getGroupsByStudentId(Long id) {

        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with this id " + id + " is not found")
                );

        return groupMapper.mapEntitiesToDtos(studentEntity.getGroups());

    }

    @Override
    public void addStudentToGroup(Long studentId, Long groupId) {

        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with this id " + studentId + " is not found")
                );

        GroupEntity groupEntity = groupRepository.findById(groupId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with this id " + groupId + " is not found")
                );

        groupEntity.getStudents().add(studentEntity);

        groupRepository.save(groupEntity);
    }

    @Override
    public Set<StudentDTO> getStudentsByGroupId(Long id) {


        GroupEntity groupEntity = groupRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with this id " + id + " is not found")
                );

        return studentMapperMapStruct.mapEntitiesToDtos(groupEntity.getStudents());

    }

    @Override
    public void removeStudentFromGroup(Long studentId, Long groupId) {

        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with this id " + studentId + " is not found")
                );

        GroupEntity groupEntity = groupRepository.findById(groupId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with this id " + groupId + " is not found")
                );

        groupEntity.getStudents().remove(studentEntity);
        studentEntity.getGroups().remove(groupEntity);

        studentRepository.save(studentEntity);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Page<StudentDTO> findAll(Pageable pageable) {
        Page<StudentEntity> studentEntities = studentRepository.findAll(pageable);
        return studentEntities.map(studentMapperMapStruct::mapEntityToDto);

    }

    @Override
    public void uploadImage(Long id, MultipartFile image) {

        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with this id " + id + " is not found")
        );

        try{
            byte[] imageBytes = image.getBytes();
            studentEntity.setProfilePicture(imageBytes);

            studentRepository.save(studentEntity);
        }catch (IOException ioe){
            throw new RuntimeException(ioe);
        }
    }

    @Override
    public ByteArrayResource readImage(Long id) {

        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with this id " + id + " is not found")
        );

        byte[] image = studentEntity.getProfilePicture();

        if(image != null){
            ByteArrayResource resource = new ByteArrayResource(image);
            return resource;
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile picture not found for the given id of student: "+id);
        }
    }
}
