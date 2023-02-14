package com.example.messages.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class MessageDto {

    private Long messageId;
    private UserDto messageFrom;
    private UserDto messageTo;
    private String messageText;
    private LocalDateTime createdAt;
}
