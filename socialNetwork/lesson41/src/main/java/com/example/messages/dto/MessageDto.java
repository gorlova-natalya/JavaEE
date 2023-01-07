package com.example.messages.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {

    private long messageId;
    private long messageFrom;
    private long messageTo;
    private String messageText;
    private LocalDateTime createdAt;
}
