package com.teachmeskills.repository;

import com.teachmeskills.model.Friend;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository {

    void addFriend(long requestFrom, long requestTo);

    Optional<Friend> getFriend(long requestFrom, long requestTo);

    List<Friend> getUserFriends(long userId);

    void deleteFriend(long requestFrom, long requestTo);
}
