create table comment
(
    cid      int auto_increment
        primary key,
    fid      int           not null,
    uid      int           not null,
    content  text          not null,
    parentid int default 0 null,
    date     timestamp     not null,
    constraint fid
        foreign key (fid) references film (fid)
            on update cascade on delete cascade,
    constraint uid
        foreign key (uid) references user (uid)
            on update cascade on delete cascade
);

