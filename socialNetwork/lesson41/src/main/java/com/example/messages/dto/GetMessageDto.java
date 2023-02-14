package com.example.messages.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetMessageDto {

    Long messageTo;
    Long messageFrom;
}
