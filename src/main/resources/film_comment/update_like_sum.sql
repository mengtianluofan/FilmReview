create definer = root@localhost trigger update_like_sum
    after insert
    on filmlike
    for each row
BEGIN
    UPDATE `film`
    SET like_sum = like_sum + 1
    WHERE fid = NEW.fid;
END;

