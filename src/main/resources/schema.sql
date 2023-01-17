DROP TABLE USERS IF EXISTS;

CREATE TABLE USERS  (
    id varchar(10) PRIMARY KEY,
    name VARCHAR(20) not null,
    password VARCHAR(10) not null
);