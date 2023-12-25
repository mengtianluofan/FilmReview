package com.myProject.entity;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author mengtianluofan
 * @version 1.0
 * @description: TODO
 * @date 2023/12/19 21:34
 */
public class FilmComment {
    private int cid;
    private String uname;
    private String content;
    private Timestamp date;
    private int parentId;
    private int fid;
    private List<FilmComment> replies = null;

    public FilmComment() {
    }

    public FilmComment(int cid, String uname, String content, Timestamp date, int parentId, int fid) {
        this.cid = cid;
        this.uname = uname;
        this.content = content;
        this.date = date;
        this.parentId = parentId;
        this.fid = fid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentid) {
        this.parentId = parentid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public List<FilmComment> getReplies() {
        return replies;
    }

    public void setReplies(List<FilmComment> replies) {
        this.replies = replies;
    }
}
