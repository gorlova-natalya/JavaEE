CREATE TABLE friends
(
    id       BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    user_id   BIGINT NOT NULL REFERENCES users(id),
    friend_id BIGINT NOT NULL REFERENCES users(id)
);