CREATE TABLE users
(
    login VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL UNIQUE
);

INSERT INTO users (login, password)
VALUES ('Andrey', "andrey111");
