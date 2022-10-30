package com.teachmeskills.repository;

import com.teachmeskills.model.FriendRequest;

import java.util.List;
import java.util.Optional;

public interface FriendRequestRepository {

    Optional<FriendRequest> getRequest(long requestFrom, long requestTo);

    void createRequest(long requestFrom, long requestTo);

    void deleteRequest(long requestFrom, long requestTo);

    List<FriendRequest> getIncomingRequests(long userId);

    List<FriendRequest> getOutcomingRequests(long inviterId);
}
