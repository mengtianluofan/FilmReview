create table user
(
    uid      int auto_increment
        primary key,
    uname    varchar(255)                          not null,
    password varchar(255)                          not null,
    role     enum ('user', 'admin') default 'user' not null
);

