package com.example.messages.repository;

import com.example.messages.dto.MessageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<MessageDto, Long> {

//    List<MessageDto> findMessageByMessageFromIdAndMessageToId(long messageFrom, long messageTo);
//
    void deleteByMessageFromAndMessageTo(long requestFrom, long requestTo);

    @Query(value = "select user_id, friend_id, message from messages " +
            " where user_id = :messageFrom and friend_id = :messageTo " +
            "or user_id = :messageTo and friend_Id = :messageFrom")
    List<MessageDto> getMessages(@Param("userId") long messageFrom, @Param("friendId") long messageTo);

    @Query("insert into messages (user_id, friend_id, message) values ( :messageFrom, :messageTo, :message)")
    @Modifying
    void saveMessage(@Param("messageFrom") long messageFrom,
                     @Param("messageTo") long messageTo,
                     @Param("message") String message);
}
