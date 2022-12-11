package com.teachmeskills.controllerrest;

import com.teachmeskills.config.jwt.Jwt;
import com.teachmeskills.dto.AuthResultDto;
import com.teachmeskills.dto.CredentialsDto;
import com.teachmeskills.facade.UserFacade;
import com.teachmeskills.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthRestController {

    private final UserFacade userFacade;
    private final Jwt jwt;

    @PostMapping
    public AuthResultDto authorize(@RequestBody final CredentialsDto credentials) {
        User user = userFacade.getUserByLoginAndPassword(credentials.getLogin(), credentials.getPassword());
        String token = jwt.generateToken(user.getLogin());
        log.info(token);
        return new AuthResultDto(token);
    }
}
