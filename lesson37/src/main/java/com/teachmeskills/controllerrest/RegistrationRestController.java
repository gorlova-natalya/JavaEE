package com.teachmeskills.controllerrest;

import com.teachmeskills.converter.UserConverter;
import com.teachmeskills.dto.CreateUserDto;
import com.teachmeskills.facade.UserFacade;
import com.teachmeskills.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/registration")
@RequiredArgsConstructor
public class RegistrationRestController {

    private final UserFacade userFacade;
    private final UserConverter userConverter;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    protected CreateUserDto createUser(@Valid @RequestBody final CreateUserDto dto) {
        final User user = userFacade.createUser(dto.getLogin(), dto.getPassword());
        log.info("User {} registered", dto.getLogin());
        return userConverter.toDto(user);
    }
}

