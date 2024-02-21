package com.zerogravitysolutions.digitalschool.trainings.commons;

import com.zerogravitysolutions.digitalschool.DTOs.TrainingDto;
import com.zerogravitysolutions.digitalschool.trainings.TrainingEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TrainingMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoToEntity(TrainingDto source, @MappingTarget TrainingEntity target);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TrainingDto mapEntityToDto(TrainingEntity source);
}
