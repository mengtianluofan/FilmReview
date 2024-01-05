create table filmlike
(
    likeid int auto_increment
        primary key,
    uid    int      not null,
    fid    int      not null,
    opTime datetime null,
    constraint unique_like
        unique (uid, fid),
    constraint fid_like
        foreign key (fid) references film (fid)
            on update cascade on delete cascade,
    constraint uid_like
        foreign key (uid) references user (uid)
            on update cascade on delete cascade
);

