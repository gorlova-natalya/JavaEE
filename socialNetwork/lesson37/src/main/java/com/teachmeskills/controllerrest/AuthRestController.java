package com.teachmeskills.controllerrest;

import com.teachmeskills.config.jwt.Jwt;
import com.teachmeskills.dto.AuthResultDto;
import com.teachmeskills.dto.CredentialsDto;
import com.teachmeskills.facade.UserFacade;
import com.teachmeskills.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthRestController {

    private final UserFacade userFacade;
    private final Jwt jwt;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthResultDto authorize(@RequestBody final CredentialsDto credentials, HttpServletResponse response) {
        User user = userFacade.getUserByLoginAndPassword(credentials.getLogin(), credentials.getPassword());
        String token = jwt.generateToken(user.getLogin());
        log.info(token);
        Cookie cookie = new Cookie("myToken", token);
        response.addCookie(cookie);
        return new AuthResultDto(token);
    }
}
