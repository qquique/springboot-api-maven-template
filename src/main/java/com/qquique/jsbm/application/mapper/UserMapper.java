package com.qquique.jsbm.application.mapper;

import com.qquique.jsbm.application.dto.UserDTO;
import com.qquique.jsbm.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO mapToDTO(User user);

    @Mapping(target="lastDateCreated", ignore = true)
    @Mapping(target="lastDateModified", ignore = true)
    User mapToDomain(UserDTO userDTO);

    @Mapping(target="lastDateCreated", ignore = true)
    @Mapping(target="lastDateModified", ignore = true)
    void updateUserFromDTO(UserDTO userDto, @MappingTarget User user);
}
