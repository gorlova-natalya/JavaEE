package com.teachmeskills.converter;

import com.teachmeskills.dto.CreateUserDto;
import com.teachmeskills.dto.UserDtoRest;
import com.teachmeskills.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserConverter {
    List<UserDtoRest> toDto(List<User> users);

    CreateUserDto toDto(User user);
}
