package com.teachmeskills.facade;

import com.teachmeskills.model.FriendRequest;
import com.teachmeskills.model.User;
import com.teachmeskills.service.FriendRequestService;
import com.teachmeskills.service.FriendService;
import com.teachmeskills.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FriendRequestFacade {

    private final FriendRequestService friendRequestService;
    private final FriendService friendService;
    private final UserService userService;

    public void createRequest(long requestFrom, long requestTo) {
        User requestFromUser = userService.getUserById(requestFrom).orElseThrow();
        User requestToUser = userService.getUserById(requestTo).orElseThrow();
        friendRequestService.createRequest(requestFromUser, requestToUser);
    }

    public List<User> getUsersByOutcomingRequests(long requestFrom) {
        return userService.getUserById(requestFrom).orElseThrow()
                .getOutcomingRequests()
                .stream()
                .map(FriendRequest::getRequestTo)
                .collect(Collectors.toList());
    }

    public List<User> getUsersByIncomingRequests(long requestTo) {
        return userService.getUserById(requestTo).orElseThrow()
                .getIncomingRequests()
                .stream()
                .map(FriendRequest::getRequestFrom)
                .collect(Collectors.toList());
    }

    public void acceptRequest(long requestFrom, long requestTo) {
        User userFrom = userService.getUserById(requestFrom).orElseThrow();
        User userTo = userService.getUserById(requestTo).orElseThrow();
        friendService.createFriend(userFrom, userTo);

        this.deleteRequest(requestFrom, requestTo);
    }

    public void deleteRequest(long requestFrom, long requestTo) {
        friendRequestService.deleteRequest(requestFrom, requestTo);
    }
}
