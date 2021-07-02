package com.example.mybookactivity.logic.dao.entity;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class BookContent extends LitePalSupport implements Serializable {

        private int id1;
        private int id2;
        private int id3;
        private int nid;//下一章节id,如果为-1则没有下一章节了
        private int pid;//上一章节id,如果为-1则没有上一章节
        private int hasContent;//为1则有内容
        private String cName;//章节名字
        private String content;//章节内容

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

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getHasContent() {
        return hasContent;
    }

    public void setHasContent(int hasContent) {
        this.hasContent = hasContent;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
