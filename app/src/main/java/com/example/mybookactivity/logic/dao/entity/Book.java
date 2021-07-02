package com.example.mybookactivity.logic.dao.entity;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;


public class Book extends LitePalSupport implements Serializable{

    private String bookName;
    private String author;
    private int id1;
    private int id2;
    private String desc;
    private String cName;
    private String lastTime;
    private int firstChapterId;
    private int lastChapterId;
    private String bookStatus;
    private double score;
    private String bookPic;

    public String getBookPic() {
        return bookPic;
    }

    public void setBookPic(String bookPic) {
        this.bookPic = bookPic;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public int getFirstChapterId() {
        return firstChapterId;
    }

    public void setFirstChapterId(int firstChapterId) {
        this.firstChapterId = firstChapterId;
    }

    public int getLastChapterId() {
        return lastChapterId;
    }

    public void setLastChapterId(int lastChapterId) {
        this.lastChapterId = lastChapterId;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
