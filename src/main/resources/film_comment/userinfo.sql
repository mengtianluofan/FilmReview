create definer = root@localhost view userinfo as
select `u`.`uid`                                                                          AS `uid`,
       `u`.`uname`                                                                        AS `uname`,
       (select count(0) from `film_comment`.`comment` `c` where (`u`.`uid` = `c`.`uid`))  AS `comment_count`,
       (select count(0) from `film_comment`.`filmlike` `l` where (`u`.`uid` = `l`.`uid`)) AS `like_count`
from `film_comment`.`user` `u`
where (`u`.`role` = 'user');

