package com.teachmeskills.service;

import com.teachmeskills.client.MessageClient;
import com.teachmeskills.dto.CreateMessageDto;
import com.teachmeskills.dto.GetMessageDto;
import com.teachmeskills.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageClient messageClient;

    public void createMessage(final CreateMessageDto createMessageDto) {
        messageClient.sendMessage(createMessageDto);
    }

    public List<MessageDto> getMessages(final GetMessageDto getMessageDto) {
        return messageClient.getMessages(getMessageDto);
    }

    public void deleteDialog(final GetMessageDto getMessageDto) {
        messageClient.deleteMessage(getMessageDto);
    }
}
