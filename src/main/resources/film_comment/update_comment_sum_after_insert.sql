create definer = root@localhost trigger update_comment_sum_after_insert
    after insert
    on comment
    for each row
BEGIN
    UPDATE film
    SET comment_sum = comment_sum + 1
    WHERE fid = NEW.fid;
END;

