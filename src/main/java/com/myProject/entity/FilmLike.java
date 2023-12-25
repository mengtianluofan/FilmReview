package com.myProject.entity;

import java.sql.Timestamp;

/**
 * @author mengtianluofan
 * @version 1.0
 * @description: 点赞的
 * TODO
 * @date 2023/12/22 17:19
 */
public class FilmLike {
    private int likeid;
    private int uid;
    private int fid;
    private Timestamp subtime;

    public FilmLike() {
    }

    public FilmLike(int likeid, int uid, int fid, Timestamp subtime) {
        this.likeid = likeid;
        this.uid = uid;
        this.fid = fid;
        this.subtime = subtime;
    }

    public int getLikeid() {
        return likeid;
    }

    public void setLikeid(int likeid) {
        this.likeid = likeid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public Timestamp getSubtime() {
        return subtime;
    }

    public void setSubtime(Timestamp subtime) {
        this.subtime = subtime;
    }
}
