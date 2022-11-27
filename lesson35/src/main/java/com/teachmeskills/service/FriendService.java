package com.teachmeskills.service;

import com.teachmeskills.model.Friend;
import com.teachmeskills.model.User;
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
        return friendRepository.getFriendsByUserId(userId);
    }

    public void createFriend(User user, User friendUser) {
        if (friendRepository.findFriendByUserAndFriend(user, friendUser).isPresent()) {
            throw new RuntimeException("Friend already created");
        }
        final Friend friend = Friend.builder().user(user).friend(friendUser).build();
        final Friend friend1 = Friend.builder().user(friendUser).friend(user).build();
        friendRepository.save(friend);
        friendRepository.save(friend1);
    }

    public void deleteFriend(long requestFrom, long requestTo) {
        friendRepository.deleteByUserIdAndFriendId(requestFrom, requestTo);
        friendRepository.deleteByUserIdAndFriendId(requestTo, requestFrom);
    }
}
