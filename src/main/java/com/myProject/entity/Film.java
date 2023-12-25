package com.myProject.entity;

public class Film {
    private int fid;
    private String fname;
    private String director;
    private int releaseYear;
    private String finfo;
    private String picture;
    private int commentSum;
    private int likeSum;

    public Film() {
    }

    public Film(int fid, String fname, String director, int releaseYear, String finfo, String picture, int commentSum, int likeSum) {
        this.fid = fid;
        this.fname = fname;
        this.director = director;
        this.releaseYear = releaseYear;
        this.finfo = finfo;
        this.picture = picture;
        this.commentSum = commentSum;
        this.likeSum = likeSum;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFinfo() {
        return finfo;
    }

    public void setFinfo(String finfo) {
        this.finfo = finfo;
    }

    public int getCommentSum() {
        return commentSum;
    }

    public void setCommentSum(int commentSum) {
        this.commentSum = commentSum;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String Director) {
        this.director = Director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getLikeSum() {
        return likeSum;
    }

    public void setLikeSum(int likeSum) {
        this.likeSum = likeSum;
    }
}