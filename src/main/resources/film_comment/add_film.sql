create
    definer = root@localhost procedure add_film(IN in_fname varchar(255), IN in_director varchar(255),
                                                IN in_releaseYear int, IN in_finfo varchar(255),
                                                IN in_picture varchar(255), OUT out_fid int)
BEGIN
    INSERT INTO film(fname, director, releaseYear, finfo, picture)
    VALUES (in_fname, in_director, in_releaseYear, in_finfo, in_picture);

    -- 获取生成的自增主键(fid)
    SET out_fid = LAST_INSERT_ID();
END;

