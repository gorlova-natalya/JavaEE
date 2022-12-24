package com.teachmeskills.repository;

import com.teachmeskills.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findMessageByMessageFromIdAndMessageToId(long messageFrom, long messageTo);

    void deleteByMessageFromIdAndMessageToId(long requestFrom, long requestTo);
}
