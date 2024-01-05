create
    definer = root@localhost procedure DeleteComment(IN comment_id int)
BEGIN
    DELETE FROM comment WHERE cid = comment_id OR parentid = comment_id;
END;

