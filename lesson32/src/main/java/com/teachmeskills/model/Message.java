package com.teachmeskills.model;

import lombok.Value;
import java.time.LocalDateTime;

@Value
public class Message {

    long messageId;
    long messageFrom;
    long messageTo;
    String messageText;
    LocalDateTime createdAt;
}
