package com.zerogravitysolutions.digitalschool.trainings.commons;

import com.zerogravitysolutions.digitalschool.DTOs.TrainingDTO;
import com.zerogravitysolutions.digitalschool.trainings.TrainingEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TrainingMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoToEntity(TrainingDTO source, @MappingTarget TrainingEntity target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TrainingDTO mapEntityToDto(TrainingEntity source);
}
