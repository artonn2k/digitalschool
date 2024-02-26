package com.zerogravitysolutions.digitalschool.groups.commons;

import com.zerogravitysolutions.digitalschool.DTOs.GroupDTO;
import com.zerogravitysolutions.digitalschool.groups.GroupEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GroupDTO mapEntityToDto(GroupEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Set<GroupDTO> mapEntitiesToDtos(Set<GroupEntity> sourceList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GroupEntity mapDtoToEntity(GroupDTO source);
}
