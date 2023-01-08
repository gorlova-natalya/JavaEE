package com.teachmeskills.controller;

import com.teachmeskills.converter.UserConverter;
import com.teachmeskills.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserConverter userConverter;

//    @Test
//    public void getUsers() throws Exception {
//        final String name = "name";
//        final UserDto user = UserDto.builder()
//                .login("Andrey")
//                .password("123")
//                .build();
//        BDDMockito.given(userService.getUserByLogin("Andrey")).willReturn(User user);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/main")
//                        .param("name", name))
//                .andExpect(status().isOk())
//                .andExpect(content().string(StringContains.containsString("Hello <span>name</span>")))
//                .andExpect(content().string(StringContains.containsString("Firstname <span>first</span>")))
//                .andExpect(content().string(StringContains.containsString("Lastname <span>last</span>")));
//    }

    @Test
    void getUsersByLogin() {
    }
}
