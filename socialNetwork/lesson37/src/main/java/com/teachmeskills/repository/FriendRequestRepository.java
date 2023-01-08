package com.teachmeskills.repository;

import com.teachmeskills.model.FriendRequest;
import com.teachmeskills.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    Optional<FriendRequest> getByRequestFromAndRequestTo(User requestFrom, User requestTo);

    void deleteByRequestFromIdAndRequestToId(long requestFrom, long requestTo);
}
