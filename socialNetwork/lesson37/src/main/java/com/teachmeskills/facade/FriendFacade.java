package com.teachmeskills.facade;

import com.teachmeskills.dto.GetMessageDto;
import com.teachmeskills.model.Friend;
import com.teachmeskills.model.User;
import com.teachmeskills.service.FriendService;
import com.teachmeskills.service.MessageService;
import com.teachmeskills.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FriendFacade {

    private final FriendService friendService;
    private final UserService userService;
    private final MessageService messageService;

    public List<User> getFriends(long userId) {
        List<Long> friendsId = friendService.getUserFriends(userId).stream()
                .map(Friend::getFriend).map(User::getId).collect(Collectors.toList());
        return userService.getUsersById(friendsId);
    }

    public void deleteFriendAndDialog(long requestFrom, long requestTo) {
        friendService.deleteFriend(requestFrom, requestTo);
        final GetMessageDto getMessageDto = GetMessageDto.builder().messageFrom(requestFrom)
                .messageTo(requestTo).build();
        messageService.deleteDialog(getMessageDto);
    }
}
