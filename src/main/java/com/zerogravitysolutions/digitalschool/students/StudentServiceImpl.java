package com.zerogravitysolutions.digitalschool.students;

import com.zerogravitysolutions.digitalschool.DTOs.GroupDTO;
import com.zerogravitysolutions.digitalschool.DTOs.StudentDTO;
import com.zerogravitysolutions.digitalschool.exceptions.GroupNotFoundException;
import com.zerogravitysolutions.digitalschool.exceptions.StudentNotFoundException;
import com.zerogravitysolutions.digitalschool.feignclients.EmailSenderFeignClient;
import com.zerogravitysolutions.digitalschool.groups.GroupEntity;
import com.zerogravitysolutions.digitalschool.groups.GroupRepository;
import com.zerogravitysolutions.digitalschool.groups.commons.GroupMapper;
import com.zerogravitysolutions.digitalschool.students.commons.StudentMapperMapStruct;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private StudentMapperMapStruct studentMapperMapStruct;
    private GroupMapper groupMapper;
    private GroupRepository groupRepository;
    private final EmailSenderFeignClient emailSenderFeignClient;
    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository,
                              StudentMapperMapStruct studentMapperMapStruct,
                              GroupRepository groupRepository, GroupMapper groupMapper,
                              EmailSenderFeignClient emailSenderFeignClient
    ) {
        this.studentRepository = studentRepository;
        this.studentMapperMapStruct = studentMapperMapStruct;
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.groupRepository = groupRepository;
        this.emailSenderFeignClient = emailSenderFeignClient;

    }

    @Override
    public StudentEntity save(StudentEntity studentEntity) {
        logger.info("Student created");
        return studentRepository.save(studentEntity);
    }

    @Override
    public StudentDTO findById(Long id) {

        StudentEntity studentEntity;
        try {
            logger.info("Attempting to find a student with ID {}", id);
            studentEntity = studentRepository.findById(id).orElseThrow(
                    () -> new StudentNotFoundException("Student with this id " + id + " is not found"));
        }catch (Exception ex){
            logger.error("Error occurred while fetching student from the database", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch student from the database", ex);
        }

        try{
            logger.info("Attempting to send the email");
            ResponseEntity<Void> responseEntity = emailSenderFeignClient.send("Hello hello", "inspireclips11@gmail.com", "Email plain text body goes here...!");

            if (!responseEntity.getStatusCode().is2xxSuccessful()) {
                logger.error("Failed to send the email . Status code: {}", responseEntity.getStatusCode());
                //throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to send the email: " + responseEntity.getStatusCode());
            }
        } catch (FeignException fe) {
            if (fe.getCause() instanceof ConnectException) {
                logger.error("Connection error occurred while sending the email" , fe);
                //throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Connection error occurred while sending the email", fe);
            } else {
                logger.error("Failed to send the email", fe);
                //throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to send the email");
            }
        }
        logger.info("Student found with ID: {}", id);
        return studentMapperMapStruct.mapEntityToDto(studentEntity);
    }

    @Override
    public StudentDTO update(Long id, StudentDTO studentDTO) {

        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException("Student with this id " + id + " is not found"));

        StudentEntity merged = studentMapperMapStruct.mapDtoToEntity(studentDTO);
        StudentEntity updated = studentRepository.save(merged);

        return studentMapperMapStruct.mapEntityToDto(updated);
    }

    @Override
    public StudentDTO patchStudent(Long id, StudentDTO studentDto) {

        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(
                        () -> new StudentNotFoundException("Student with this id " + id + " is not found"));


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
                .orElseThrow(() -> new StudentNotFoundException("Student with this id " + id + " is not found"));

        return groupMapper.mapEntitiesToDtos(studentEntity.getGroups());

    }

    @Override
    public void addStudentToGroup(Long studentId, Long groupId) {

        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student with this id " + studentId + " is not found"));

        GroupEntity groupEntity = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Group with this id" + groupId + "is not found"));

        groupEntity.getStudents().add(studentEntity);

        groupRepository.save(groupEntity);
    }

    @Override
    public Set<StudentDTO> getStudentsByGroupId(Long id) {


        GroupEntity groupEntity = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group with this id" + id + "is not found"));

        return studentMapperMapStruct.mapEntitiesToDtos(groupEntity.getStudents());

    }

    @Override
    public void removeStudentFromGroup(Long studentId, Long groupId) {

        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student with this id " + studentId + " is not found"));

        GroupEntity groupEntity = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Group with this id" + groupId + "is not found"));

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

        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with this id " + id + " is not found"));

        try {
            byte[] imageBytes = image.getBytes();
            studentEntity.setProfilePicture(imageBytes);

            studentRepository.save(studentEntity);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Override
    public ByteArrayResource readImage(Long id) {

        StudentEntity studentEntity = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with this id " + id + " is not found"));

        byte[] image = studentEntity.getProfilePicture();

        if (image != null) {
            ByteArrayResource resource = new ByteArrayResource(image);
            return resource;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile picture not found for the given id of student: " + id);
        }
    }
}
