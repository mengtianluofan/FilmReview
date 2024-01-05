create
    definer = root@localhost procedure GetFilmComments(IN film_id int)
BEGIN
    SELECT * FROM `comment` WHERE `fid` = film_id ORDER BY `date` DESC;
END;

