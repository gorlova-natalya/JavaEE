package com.example.messages.converter;

import com.example.messages.dto.MessageDto;
import com.example.messages.model.Message;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MessageConverter {

    List<MessageDto> toDto(final List<Message> message);
}
