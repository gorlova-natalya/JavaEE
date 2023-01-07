CREATE TABLE invitations
(
    id         BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    inviter_id BIGINT    NOT NULL REFERENCES users (id),
    user_id    BIGINT    NOT NULL REFERENCES users (id)
);