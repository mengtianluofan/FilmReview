create
    definer = root@localhost procedure update_film(IN in_fid int, IN in_fname varchar(255), IN in_director varchar(255),
                                                   IN in_releaseYear int, IN in_finfo varchar(255),
                                                   IN in_picture varchar(255))
BEGIN
    UPDATE film
    SET fname       = in_fname,
        director    = in_director,
        releaseYear = in_releaseYear,
        finfo       = in_finfo,
        picture     = in_picture
    WHERE fid = in_fid;
END;

