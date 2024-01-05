create table film
(
    fid         int auto_increment
        primary key,
    fname       varchar(255)  not null,
    director    varchar(255)  null,
    releaseYear int           null,
    finfo       varchar(255)  null,
    picture     varchar(255)  null,
    comment_sum int default 0 null,
    like_sum    int default 0 null
);

