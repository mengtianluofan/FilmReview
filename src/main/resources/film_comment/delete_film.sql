create
    definer = root@localhost procedure delete_film(IN in_fid int)
BEGIN
    DELETE FROM film WHERE fid = in_fid;
END;

