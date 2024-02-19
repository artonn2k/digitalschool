package com.zerogravitysolutions.digitalschool.students.commons;

import com.zerogravitysolutions.digitalschool.DTOs.StudentDto;
import com.zerogravitysolutions.digitalschool.students.StudentEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Set;



@Mapper(componentModel = "spring")
public interface StudentMapperMapStruct {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoToEntity(StudentDto source,@MappingTarget StudentEntity target);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StudentDto mapEntityToDto(StudentEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Set<StudentDto> mapEntitiesToDtos(Set<StudentEntity> sourceList);

}
