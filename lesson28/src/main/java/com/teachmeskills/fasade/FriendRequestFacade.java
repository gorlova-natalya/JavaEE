package com.teachmeskills.fasade;

import com.teachmeskills.model.FriendRequest;
import com.teachmeskills.model.User;
import com.teachmeskills.service.FriendRequestService;
import com.teachmeskills.service.UserService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FriendRequestFacade {

    private final FriendRequestService friendRequestService;
    private final UserService userService;

    public void createRequest(long requestFrom, long requestTo) {
        friendRequestService.createRequest(requestFrom, requestTo);
    }

    public List<User> getUsersByOutcomingRequests(long requestFrom) {
        return friendRequestService.getOutcomingRequests(requestFrom).stream()
                .map(FriendRequest::getRequestTo)
                .map(userService::getUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public List<User> getUsersByIncomingRequests(long requestTo) {
        return friendRequestService.getIncomingRequests(requestTo).stream()
                .map(FriendRequest::getRequestFrom)
                .map(userService::getUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public void acceptRequest(long requestFrom, long requestTo) {
        friendRequestService.acceptRequest(requestFrom, requestTo);
    }

    public void deleteRequest(long requestFrom, long requestTo) {
        friendRequestService.deleteRequest(requestFrom, requestTo);
    }
}
