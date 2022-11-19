package com.teachmeskills.dto;

import lombok.Data;

@Data
public class DeleteRequestDto {

    private final String revokeFr;
    private final String redirect;
}
