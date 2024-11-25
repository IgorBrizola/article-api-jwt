CREATE TABLE article(
id UNIQUEIDENTIFIER PRIMARY KEY,
title VARCHAR(255) NOT NULL,
content VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    id UNIQUEIDENTIFIER PRIMARY KEY,
    email NVARCHAR(255) NOT NULL UNIQUE,
    password NVARCHAR(MAX) NOT NULL,
    role NVARCHAR(50) NOT NULL
);