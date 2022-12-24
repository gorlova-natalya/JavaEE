package com.teachmeskills.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FriendDtoRest {

    private Long id;
    private UserDtoRest user;
    private UserDtoRest friend;
}
