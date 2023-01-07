package com.example.messages.converter;

import com.example.messages.dto.MessageDto;
import com.example.messages.model.Message;
import org.mapstruct.Mapper;

@Mapper
public interface MessageConverter {

    MessageDto toDto(final Message message);
}
