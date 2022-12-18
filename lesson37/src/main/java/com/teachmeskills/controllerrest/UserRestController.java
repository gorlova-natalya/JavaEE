package com.teachmeskills.controllerrest;

import com.teachmeskills.converter.UserConverter;
import com.teachmeskills.dto.UserDtoRest;
import com.teachmeskills.facade.UserFacade;
import com.teachmeskills.model.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Users API")
public class UserRestController {

    private final UserFacade userFacade;
    private final UserConverter userConverter;

    @GetMapping
    protected List<UserDtoRest> getUsers(@RequestParam(defaultValue = "1", name = "page", required = false) Integer pageNo,
                                         @RequestParam(defaultValue = "5", name = "pageSize", required = false) Integer pageSize) {
        final Page<User> page = userFacade.findPaginatedUsers(pageNo, pageSize);
        List<User> listUsers = page.getContent();
        return userConverter.toDto(listUsers);
    }

    @GetMapping("/{login}")
    protected List<UserDtoRest> getUsersByLogin(@PathVariable("login") String login) {
        if (StringUtils.isNotBlank(login)) {
            List<User> users = userFacade.findUsersStartWith(login);
            return userConverter.toDto(users);
        }
        return null;
    }
}
