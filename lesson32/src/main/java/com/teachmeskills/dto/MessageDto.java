package com.teachmeskills.dto;

import lombok.Data;

@Data
public class MessageDto {
    private final long messageTo;
    private final String message;
}
