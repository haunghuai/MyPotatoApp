package com.example.mybookactivity.logic.model;

import java.io.Serializable;
import java.util.List;

public class BookSearch implements Serializable {

    /**
     * status : 1
     * info : success
     * data : [{"Id":"608945","Name":"我开局震惊了女帝","Author":"隔江","Img":"https://imgapi.jiaston.com/BookFiles/Bookimages/wokaijuzhenjinglenvdi.jpg","Desc":"宁天来到天玄世界，睁眼竟然是女子的闺房？等等，这是什么情况？身为废物的他，绝美至极的女帝竟要在今晚和他成亲？！震惊！系统激活！震惊他人就能获得奖励！？开局震惊女帝，踏出人生大事第一步，完成举世震惊！\u2026\u2026宁天看向无数天骄圣子：\u201c不是吧\u2026\u2026我就随随便便修炼一下，都能让你们如此震惊？\u201d【简介无能，请移步正文！】","BookStatus":"完成","LastChapterId":"3426402","LastChapter":"第805章 天玄界主,震惊女帝! 大结局","CName":"武侠仙侠","UpdateTime":"2021-05-29 00:00:00","weekHitCount":"27448","monthHitCount":"44347","hitCount":"775846"},{"Id":"621059","Name":"我开局震惊了女帝","Author":"宁天洛无情","Img":"https://imgapi.jiaston.com/BookFiles/Bookimages/wokaijuzhenjinglenvdi.jpg","Desc":"宁天来到天玄世界，睁眼竟然是女子的闺房？等等，这是什么情况？身为废物的他，绝美至极的女帝竟要在今晚和他成亲？！震惊！系统激活！震惊他人就能获得奖励！？开局震惊女帝，踏出人生大事第一步，完成举世震惊！\u2026\u2026宁天看向无数天骄圣子：\u201c不是吧\u2026\u2026我就随随便便修炼一下，都能让你们如此震惊？\u201d","BookStatus":"完成","LastChapterId":"4087484","LastChapter":"第805章 天玄界主,震惊女帝! 大结局","CName":"武侠仙侠","UpdateTime":"2021-05-29 00:00:00","weekHitCount":"1037","monthHitCount":"1762","hitCount":"52951"}]
     */

    private int status;
    private String info;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * Id : 608945
         * Name : 我开局震惊了女帝
         * Author : 隔江
         * Img : https://imgapi.jiaston.com/BookFiles/Bookimages/wokaijuzhenjinglenvdi.jpg
         * Desc : 宁天来到天玄世界，睁眼竟然是女子的闺房？等等，这是什么情况？身为废物的他，绝美至极的女帝竟要在今晚和他成亲？！震惊！系统激活！震惊他人就能获得奖励！？开局震惊女帝，踏出人生大事第一步，完成举世震惊！……宁天看向无数天骄圣子：“不是吧……我就随随便便修炼一下，都能让你们如此震惊？”【简介无能，请移步正文！】
         * BookStatus : 完成
         * LastChapterId : 3426402
         * LastChapter : 第805章 天玄界主,震惊女帝! 大结局
         * CName : 武侠仙侠
         * UpdateTime : 2021-05-29 00:00:00
         * weekHitCount : 27448
         * monthHitCount : 44347
         * hitCount : 775846
         */

        private String Id;
        private String Name;
        private String Author;
        private String Img;
        private String Desc;
        private String BookStatus;
        private String LastChapterId;
        private String LastChapter;
        private String CName;
        private String UpdateTime;
        private String weekHitCount;
        private String monthHitCount;
        private String hitCount;

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getAuthor() {
            return Author;
        }

        public void setAuthor(String author) {
            Author = author;
        }

        public String getImg() {
            return Img;
        }

        public void setImg(String img) {
            Img = img;
        }

        public String getDesc() {
            return Desc;
        }

        public void setDesc(String desc) {
            Desc = desc;
        }

        public String getBookStatus() {
            return BookStatus;
        }

        public void setBookStatus(String bookStatus) {
            BookStatus = bookStatus;
        }

        public String getLastChapterId() {
            return LastChapterId;
        }

        public void setLastChapterId(String lastChapterId) {
            LastChapterId = lastChapterId;
        }

        public String getLastChapter() {
            return LastChapter;
        }

        public void setLastChapter(String lastChapter) {
            LastChapter = lastChapter;
        }

        public String getCName() {
            return CName;
        }

        public void setCName(String CName) {
            this.CName = CName;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String updateTime) {
            UpdateTime = updateTime;
        }

        public String getWeekHitCount() {
            return weekHitCount;
        }

        public void setWeekHitCount(String weekHitCount) {
            this.weekHitCount = weekHitCount;
        }

        public String getMonthHitCount() {
            return monthHitCount;
        }

        public void setMonthHitCount(String monthHitCount) {
            this.monthHitCount = monthHitCount;
        }

        public String getHitCount() {
            return hitCount;
        }

        public void setHitCount(String hitCount) {
            this.hitCount = hitCount;
        }
    }
}
