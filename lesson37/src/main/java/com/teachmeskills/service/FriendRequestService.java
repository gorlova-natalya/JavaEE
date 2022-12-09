package com.teachmeskills.service;

import com.teachmeskills.model.FriendRequest;
import com.teachmeskills.model.User;
import com.teachmeskills.repository.FriendRequestRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;

    public void createRequest(User requestFrom, User requestTo) {
        if (friendRequestRepository.getByRequestFromAndRequestTo(requestFrom, requestTo).isEmpty() &&
                friendRequestRepository.getByRequestFromAndRequestTo(requestTo, requestFrom).isEmpty()) {
            final FriendRequest friendRequest = FriendRequest.builder().requestFrom(requestFrom).requestTo(requestTo).build();
            friendRequestRepository.save(friendRequest);
        }
    }

    public void deleteRequest(long requestFrom, long requestTo) {
        friendRequestRepository.deleteByRequestFromIdAndRequestToId(requestFrom, requestTo);
        friendRequestRepository.deleteByRequestFromIdAndRequestToId(requestTo, requestFrom);
    }
}
