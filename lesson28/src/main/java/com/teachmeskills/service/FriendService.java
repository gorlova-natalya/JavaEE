package com.teachmeskills.service;

import com.teachmeskills.model.Friend;
import com.teachmeskills.repository.FriendRepository;

import java.util.List;

public class FriendService {

    private final FriendRepository friendRepository;

    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public List<Friend> getUserFriends(long userId) {
        return friendRepository.getUserFriends(userId);
    }
}
