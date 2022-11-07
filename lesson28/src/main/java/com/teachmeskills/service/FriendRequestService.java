package com.teachmeskills.service;

import com.teachmeskills.model.FriendRequest;
import com.teachmeskills.model.User;
import com.teachmeskills.repository.FriendRepository;
import com.teachmeskills.repository.FriendRequestRepository;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;
    private final FriendRepository friendRepository;
    private final UserService userService;

    public FriendRequestService(FriendRequestRepository friendRequestRepository, FriendRepository friendRepository, UserService userService) {
        this.friendRequestRepository = friendRequestRepository;
        this.friendRepository = friendRepository;
        this.userService = userService;
    }

    public void createRequest(long requestFrom, long requestTo) {
        if (friendRequestRepository.getRequest(requestFrom, requestTo).isPresent() ||
                friendRequestRepository.getRequest(requestTo, requestFrom).isPresent()) {
            throw new RuntimeException("Request already send");
        }
        friendRequestRepository.createRequest(requestFrom, requestTo);
    }

    public void acceptRequest(long requestFrom, long requestTo) {
        if (friendRepository.getFriend(requestFrom, requestTo).isPresent()) {
            throw new RuntimeException("Friend already added");
        }
        friendRepository.addFriend(requestFrom, requestTo);
        this.deleteRequest(requestFrom, requestTo);
    }

    public void deleteRequest(long requestFrom, long requestTo) {
        if (friendRequestRepository.getRequest(requestFrom, requestTo).isEmpty()) {
            throw new RuntimeException("Request not exist");
        }
        friendRequestRepository.deleteRequest(requestFrom, requestTo);
    }

    public List<User> getUsersByOutcomingRequests(long requestFrom) {
        return friendRequestRepository.getOutcomingRequests(requestFrom).stream()
                .map(FriendRequest::getRequestTo)
                .map(userService::getUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public List<User> getUsersByIncomingRequests(long requestTo) {
        return friendRequestRepository.getIncomingRequests(requestTo).stream()
                .map(FriendRequest::getRequestFrom)
                .map(userService::getUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
