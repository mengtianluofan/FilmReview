package com.myProject.entity;

/**
 * @author mengtianluofan
 * @version 1.0
 * @description: TODO
 * @date 2023/12/22 20:17
 */
public class UserInfo {
    private int uid;
    private String uname;
    private int commentNum;
    private int likeNum;
    private String likeFilms;

    public UserInfo() {
    }

    public UserInfo(int uid, String uname, int commentNum, int likeNum) {
        this.uid = uid;
        this.uname = uname;
        this.commentNum = commentNum;
        this.likeNum = likeNum;
    }

    public UserInfo(int uid, String uname, int commentNum, int likeNum, String likeFilms) {
        this.uid = uid;
        this.uname = uname;
        this.commentNum = commentNum;
        this.likeNum = likeNum;
        this.likeFilms = likeFilms;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public String getLikeFilms() {
        return likeFilms;
    }

    public void setLikeFilms(String likeFilms) {
        this.likeFilms = likeFilms;
    }
}
