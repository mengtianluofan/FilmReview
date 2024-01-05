create
    definer = root@localhost procedure GetTopLevelCommentsByFid(IN filmId int)
BEGIN
    SELECT *
    FROM film_comments
    WHERE fid = filmId
      AND parentid = 0
    ORDER BY date DESC;
END;

