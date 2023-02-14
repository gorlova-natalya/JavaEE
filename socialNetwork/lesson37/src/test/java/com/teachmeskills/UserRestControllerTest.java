package com.teachmeskills;

import com.teachmeskills.config.filter.JwtFilter;
import com.teachmeskills.controllerrest.UserRestController;
import com.teachmeskills.dto.UserDto;
import com.teachmeskills.facade.UserFacade;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserRestController.class, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        value = JwtFilter.class)})
public class UserRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserFacade userFacade;

    @Test
    public void shouldReturnA403WhenUserIsNotLoggedIn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isForbidden());
    }

    @Test
    public void shouldReturnA403WhenUserIsLoggedIn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/users")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void shouldGetUserFromUserService() throws Exception {
        final String login = "Andrey";
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/users/{login}", login)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
        then(userFacade)
                .should()
                .getUser(login);
    }

    @Test
    @WithUserDetails
    public void shouldReturn200whenCreatingAUser() throws Exception {
        final String username = "username";
        final String password = "password";
        UserDto userDto = new UserDto();
        userDto.setPassword(password);
        userDto.setLogin(username);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/users", userDto)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
