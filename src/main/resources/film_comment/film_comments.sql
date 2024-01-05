create definer = root@localhost view film_comments as
select `c`.`cid`      AS `cid`,
       `u`.`uname`    AS `uname`,
       `c`.`content`  AS `content`,
       `c`.`date`     AS `date`,
       `c`.`parentid` AS `parentid`,
       `c`.`fid`      AS `fid`
from (`film_comment`.`comment` `c` join `film_comment`.`user` `u` on ((`c`.`uid` = `u`.`uid`)))
order by `c`.`date` desc;

