create definer = root@localhost view filmlike_foruser as
select `fl`.`uid` AS `uid`, `f`.`fname` AS `fname`, `fl`.`opTime` AS `opTime`
from (`film_comment`.`filmlike` `fl` join `film_comment`.`film` `f` on ((`fl`.`fid` = `f`.`fid`)));

