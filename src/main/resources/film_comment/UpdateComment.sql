create
    definer = root@localhost procedure UpdateComment(IN comment_id int, IN film_id int, IN user_id int,
                                                     IN comment_content text, IN parent_comment_id int,
                                                     IN comment_date timestamp)
BEGIN
    UPDATE comment
    SET fid      = film_id,
        uid      = user_id,
        content  = comment_content,
        parentid = parent_comment_id,
        date     = comment_date
    WHERE cid = comment_id;
END;

