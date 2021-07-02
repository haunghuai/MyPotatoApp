package com.example.mybookactivity.logic.dao.entity;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class BookChapter extends LitePalSupport implements Serializable {
        private int id1;
        private int id2;
        private int id3;
        private String bookName;
        private String bookVolume; //书的卷名
        private String bookChapterName;

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

    public int getId3() {
        return id3;
    }

    public void setId3(int id3) {
        this.id3 = id3;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookVolume() {
        return bookVolume;
    }

    public void setBookVolume(String bookVolume) {
        this.bookVolume = bookVolume;
    }

    public String getBookChapterName() {
        return bookChapterName;
    }

    public void setBookChapterName(String bookChapterName) {
        this.bookChapterName = bookChapterName;
    }
}
