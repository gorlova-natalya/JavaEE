package com.teachmeskills.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "messages")
public class Message {

    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long messageId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User messageFrom;

    @ManyToOne
    @JoinColumn(name = "friend_id", referencedColumnName = "id")
    private User messageTo;

    @Column(name = "message")
    private String messageText;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
