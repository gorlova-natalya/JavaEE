package com.teachmeskills.dto;

import com.teachmeskills.model.User;
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
