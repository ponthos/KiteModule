package com.jiayuan.jr.modelmodule.ResponseModel;

public class ArticResponse {

    /**
     * date : 1552366800000
     * vid : 1
     * author : setAuthor
     * md : setMd
     * title : setTitle
     * article : setArticle
     * tags : setTags
     */

    private long date;
    private int vid;
    private String author;
    private String md;
    private String title;
    private String article;
    private String tags;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}