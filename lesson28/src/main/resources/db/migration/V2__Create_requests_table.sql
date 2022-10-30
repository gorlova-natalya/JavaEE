CREATE TABLE invitations
(
    inviter_id BIGINT    NOT NULL,
    user_id    BIGINT    NOT NULL,

    PRIMARY KEY (inviter_id, user_id)
);