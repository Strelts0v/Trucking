package com.itechart.trucking.service.mapper;

import com.itechart.trucking.domain.User;
import com.itechart.trucking.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for the entity {@link User} and its DTO called {@link UserDto}.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-22
 */
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "birthday", target = "birthday", dateFormat = "dd.MM.yyyy")
    UserDto userToUserDto(User user);
}