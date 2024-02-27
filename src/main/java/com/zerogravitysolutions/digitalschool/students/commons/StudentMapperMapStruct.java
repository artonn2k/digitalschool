package com.zerogravitysolutions.digitalschool.students.commons;

import com.zerogravitysolutions.digitalschool.DTOs.StudentDTO;
import com.zerogravitysolutions.digitalschool.students.StudentEntity;
import com.zerogravitysolutions.digitalschool.trainings.subjects.SubjectEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Set;



@Mapper(componentModel = "spring")
public interface StudentMapperMapStruct {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoToEntity(StudentDTO source, @MappingTarget StudentEntity target);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StudentDTO mapEntityToDto(StudentEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Set<StudentDTO> mapEntitiesToDtos(Set<StudentEntity> sourceList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    List<StudentDTO> mapEntitiesToDtos(List<StudentEntity> sourceList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StudentEntity mapDtoToEntity(StudentDTO source);

}
