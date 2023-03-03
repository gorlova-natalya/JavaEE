package com.example.messages.repository;

import com.example.messages.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findMessageDtoByMessageFromAndMessageTo(long messageFrom, long messageTo);

    void deleteByMessageFromAndMessageTo(long requestFrom, long requestTo);
}
