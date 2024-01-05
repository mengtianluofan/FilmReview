create
    definer = root@localhost procedure AddFilmLike(IN in_uid int, IN in_fid int)
BEGIN
    DECLARE existing_likeid INT;

    -- Check if the user has already liked the movie
    SELECT likeid
    INTO existing_likeid
    FROM `filmlike`
    WHERE uid = in_uid
      AND fid = in_fid;

    -- If not liked, then insert a new like
    IF existing_likeid IS NULL THEN
        INSERT INTO `filmlike` (uid, fid, opTime)
        VALUES (in_uid, in_fid, NOW());
    END IF;
END;

