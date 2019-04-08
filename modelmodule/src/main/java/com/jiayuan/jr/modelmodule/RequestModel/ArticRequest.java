package com.jiayuan.jr.modelmodule.RequestModel;

public class ArticRequest {

    /**
     * function : 100011
     * date :
     * vid : 0
     * author : setAuthor
     * md : setMd
     * monthDay : Oct04
     * title : setTitle
     * article : setArticle
     * tags : setTags
     */

    private int function;
    private String date;
    private int vid;
    private String author;
    private String md;
    private String monthDay;
    private String title;
    private String article;
    private String tags;

    public int getFunction() {
        return function;
    }

    public void setFunction(int function) {
        this.function = function;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(String monthDay) {
        this.monthDay = monthDay;
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
