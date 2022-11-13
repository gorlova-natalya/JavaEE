CREATE TABLE messages
(
    message_id BIGSERIAL NOT NULL UNIQUE,
    user_id    BIGINT    NOT NULL,
    friend_id  BIGINT    NOT NULL,
    message    VARCHAR   NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);
