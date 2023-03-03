package com.example.messages.converter;

import com.example.messages.dto.UserDto;
import com.example.messages.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserConverter {

    List<UserDto> toDto(List<User> users);
}
