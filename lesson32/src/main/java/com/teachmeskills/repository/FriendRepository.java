package com.teachmeskills.repository;

import com.teachmeskills.model.Friend;
import java.util.List;
import java.util.Optional;

public interface FriendRepository {

    void addFriend(long requestFrom, long requestTo);

    Optional<Friend> getFriend(long requestFrom, long requestTo);

    List<Friend> getUserFriends(long userId);

    void deleteFriend(long requestFrom, long requestTo);
}
