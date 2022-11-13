package com.teachmeskills.service;

import com.teachmeskills.model.FriendRequest;
import com.teachmeskills.repository.FriendRepository;
import com.teachmeskills.repository.FriendRequestRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;
    private final FriendRepository friendRepository;


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

    public List<FriendRequest> getOutcomingRequests(long inviterId) {
        return friendRequestRepository.getOutcomingRequests(inviterId);
    }

    public List<FriendRequest> getIncomingRequests(long userId) {
        return friendRequestRepository.getIncomingRequests(userId);
    }
}
