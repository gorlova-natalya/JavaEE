CREATE TABLE users
(
    login      VARCHAR   NOT NULL UNIQUE,
    password   VARCHAR   NOT NULL
);

INSERT INTO users (login, password)
VALUES ('Andrey', '123');
INSERT INTO users (login, password)
VALUES ('Andrey1', '123');
INSERT INTO users (login, password)
VALUES ('Andrey2', '123');
INSERT INTO users (login, password)
VALUES ('Andrey3', '123');
INSERT INTO users (login, password)
VALUES ('Andrey4', '123');