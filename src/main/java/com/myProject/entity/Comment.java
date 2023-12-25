package com.myProject.entity;

import java.sql.Timestamp;

/**
 * @author mengtianluofan
 * @version 1.0
 * @description: TODO
 * @date 2023/12/15 18:31
 */
public class Comment {
    private int cid;
    private int fid;
    private int uid;
    private String content;
    private int parentid;
    private Timestamp date;

    public Comment() {
    }

    public Comment(int cid, int fid, int uid, String content, int parentid, Timestamp date) {
        this.cid = cid;
        this.fid = fid;
        this.uid = uid;
        this.content = content;
        this.parentid = parentid;
        this.date = date;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
