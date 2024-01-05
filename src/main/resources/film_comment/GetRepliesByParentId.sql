create
    definer = root@localhost procedure GetRepliesByParentId(IN parentId int)
BEGIN
    SELECT *
    FROM film_comments
    WHERE film_comments.parentid = parentId
    ORDER BY date DESC;
END;

