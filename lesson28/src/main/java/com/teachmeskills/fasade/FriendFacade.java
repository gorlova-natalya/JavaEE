package com.teachmeskills.fasade;

import com.teachmeskills.model.Friend;
import com.teachmeskills.model.User;
import com.teachmeskills.service.FriendService;
import com.teachmeskills.service.MessageService;
import com.teachmeskills.service.UserService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FriendFacade {

    private final FriendService friendService;
    private final UserService userService;
    private final MessageService messageService;

    public List<User> getFriends(long userId) {
        return  friendService.getUserFriends(userId).stream()
                .map(Friend::getFriendId)
                .map(userService::getUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public void deleteFriendAndDialog(long requestFrom, long requestTo) {
        friendService.deleteFriend(requestFrom, requestTo);
        messageService.deleteDialog(requestFrom, requestTo);
    }
}
