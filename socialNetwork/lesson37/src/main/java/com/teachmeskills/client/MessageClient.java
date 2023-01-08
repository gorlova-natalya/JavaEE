package com.teachmeskills.client;

import com.teachmeskills.dto.MessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "message", url = "${services.message.url}")
public interface MessageClient {
    @RequestMapping(method = RequestMethod.GET, value = "/messages/{friendId}")
    List<MessageDto> getMessages(@PathVariable("friendId") final int friendId,
                                    @RequestParam("userId") final int userId);

    @RequestMapping(method = RequestMethod.POST, value = "/send-message/{friendId}")
    void sendMessage(@PathVariable("friendId") final int friendId,
                     @RequestParam("message") String message,
                     @RequestParam("userId") int userId);
}
