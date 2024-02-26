package com.zerogravitysolutions.digitalschool.trainings.subjects;

import com.zerogravitysolutions.digitalschool.DTOs.StudentDTO;
import com.zerogravitysolutions.digitalschool.DTOs.SubjectDTO;
import com.zerogravitysolutions.digitalschool.students.StudentEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void mapDtoToEntity(SubjectDTO source, @MappingTarget SubjectEntity target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SubjectDTO mapEntityToDto(SubjectEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SubjectEntity mapDtoToEntity(SubjectDTO source);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Set<SubjectDTO> mapEntitiesToDtos(Set<SubjectEntity> sourceList);

}
