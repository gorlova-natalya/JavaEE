package com.teachmeskills.service;

import com.teachmeskills.model.User;
import com.teachmeskills.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@Disabled
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService sut;

    @Test
    void shouldCreateUserWhenUserNotExist() {
        final String login = "any_login";
        final String password = "any_password";

        given(userRepository.getUserByLogin(login)).willReturn(Optional.empty());

        sut.createUser(login, password);

        then(userRepository)
                .should()
                .createUser(login, password);
    }

    @Test
    void shouldThrowExceptionWHenUserExists() {
        final String login = "any_Login";
        final String password = "any_password";
        final User user = mock(User.class);

        given(userRepository.getUserByLogin(login)).willReturn(Optional.of(user));

        final RuntimeException actual = assertThrows(
                RuntimeException.class, () -> sut.createUser(login, password));

        assertThat(actual)
                .hasMessage("User already exists");
    }

    @Test
    void shouldThrowExceptionWhenEmptyPassword() {
        final String login = "any_Login";
        final String password = "";
        this.userRepository.createUser(login, password);

        final RuntimeException actual = assertThrows(
                RuntimeException.class, () -> sut.createUser(login, password));

        assertThat(actual)
                .hasMessage("User password is empty");
    }
}
