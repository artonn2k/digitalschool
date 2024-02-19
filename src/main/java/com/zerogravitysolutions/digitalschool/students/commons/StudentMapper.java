package com.zerogravitysolutions.digitalschool.students.commons;

import com.zerogravitysolutions.digitalschool.DTOs.StudentDto;
import com.zerogravitysolutions.digitalschool.students.StudentEntity;

public class StudentMapper {

    public static void mapDtoToEntity(StudentDto source, StudentEntity target){

        if(source.getFirstName()!=null){
            target.setFirstName(source.getFirstName());
        }

        if(source.getLastName()!=null){
            target.setLastName(source.getLastName());
        }

        if(source.getProfilePicture()!=null){
            target.setProfilePicture(source.getProfilePicture());
        }

        if(source.getEmail()!=null){
            target.setEmail(source.getEmail());
        }

        if(source.getPhoneNumber()!=null){
            target.setPhoneNumber(source.getPhoneNumber());
        }

        if(source.getAddress()!=null){
            target.setAddress(source.getAddress());
        }
    }

    public static StudentDto mapEntityToDto(StudentEntity source){
        StudentDto studentDto = new StudentDto();

        studentDto.setId(source.getId());
        studentDto.setFirstName(source.getFirstName());
        studentDto.setLastName(source.getLastName());
        studentDto.setProfilePicture(source.getProfilePicture());
        studentDto.setEmail(source.getEmail());
        studentDto.setPhoneNumber(source.getPhoneNumber());
        studentDto.setAddress(source.getAddress());
        studentDto.setCreatedAt(source.getCreatedAt());
        studentDto.setCreatedBy(source.getCreatedBy());
        studentDto.setUpdatedAt(source.getUpdatedAt());
        studentDto.setUpdatedBy(source.getUpdatedBy());
        studentDto.setDeletedAt(source.getDeletedAt());
        studentDto.setDeletedBy(source.getDeletedBy());



        return studentDto;
    }
}
