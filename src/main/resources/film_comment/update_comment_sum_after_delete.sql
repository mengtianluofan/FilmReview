create definer = root@localhost trigger update_comment_sum_after_delete
    after delete
    on comment
    for each row
BEGIN
    UPDATE film
    SET comment_sum = comment_sum - 1
    WHERE fid = OLD.fid;
END;

