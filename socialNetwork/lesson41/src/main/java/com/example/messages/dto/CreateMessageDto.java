package com.example.messages.dto;

import com.example.messages.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class CreateMessageDto {

    private User messageFrom;
    private User messageTo;
    private String messageText;
}
