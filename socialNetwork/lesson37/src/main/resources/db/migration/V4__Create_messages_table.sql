CREATE TABLE messages
(
    message_id BIGSERIAL NOT NULL UNIQUE,
    user_id    BIGINT    NOT NULL REFERENCES users(id),
    friend_id  BIGINT    NOT NULL REFERENCES users(id),
    message    VARCHAR   NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);
