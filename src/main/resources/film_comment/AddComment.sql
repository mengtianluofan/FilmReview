create
    definer = root@localhost procedure AddComment(IN film_id int, IN user_id int, IN comment_content text,
                                                  IN parent_comment_id int, IN comment_date timestamp)
BEGIN
    INSERT INTO comment(fid, uid, content, parentid, date)
    VALUES (film_id, user_id, comment_content, parent_comment_id, comment_date);
END;

