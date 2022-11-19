package com.teachmeskills.service;

import com.teachmeskills.model.Friend;
import com.teachmeskills.repository.FriendRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    private final FriendRepository friendRepository;

    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public List<Friend> getUserFriends(long userId) {
        return friendRepository.getUserFriends(userId);
    }

    public void deleteFriend(long requestFrom, long requestTo) {
        friendRepository.deleteFriend(requestFrom, requestTo);
    }
}
