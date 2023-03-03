package com.teachmeskills.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {

    private long messageId;
    private UserDto messageFrom;
    private UserDto messageTo;
    private String messageText;
    private LocalDateTime createdAt;
}
