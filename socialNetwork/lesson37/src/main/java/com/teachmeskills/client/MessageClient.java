package com.teachmeskills.client;

import com.teachmeskills.dto.CreateMessageDto;
import com.teachmeskills.dto.GetMessageDto;
import com.teachmeskills.dto.MessageDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "domain", url = "${services.message.url}/api/v1/messages")
public interface MessageClient {
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    @Headers(value = "Content-Type: application/json")
    List<MessageDto> getMessages(final GetMessageDto getMessageDto);

    @RequestMapping(method = RequestMethod.POST, value = "/send")
    @Headers(value = "Content-Type: application/json")
    void sendMessage(final CreateMessageDto createMessageDto);

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    void deleteMessage(final GetMessageDto getMessageDto);
}
