package com.teachmeskills.repository;

import com.teachmeskills.model.Friend;
import com.teachmeskills.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FriendRepository extends JpaRepository<Friend, Long> {

    Optional<Friend> findFriendByUserAndFriend(User userFrom, User userT);

    List<Friend> getFriendsByUserId(long userId);

    void deleteByUserIdAndFriendId(long requestFrom, long requestTo);
}
