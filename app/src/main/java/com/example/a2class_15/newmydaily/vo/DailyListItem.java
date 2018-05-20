package com.example.a2class_15.newmydaily.vo;

import java.io.Serializable;

public class DailyListItem implements Serializable{
    private String num;
    private String title;
    private String content;
    private String date;

    public DailyListItem(){}

    public DailyListItem(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public DailyListItem(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
