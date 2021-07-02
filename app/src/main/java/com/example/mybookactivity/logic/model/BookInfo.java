package com.example.mybookactivity.logic.model;

import java.io.Serializable;
import java.util.List;


public class BookInfo implements Serializable {

    /**
     * status : 1
     * info : success
     * data : {"Id":608945,"Name":"我开局震惊了女帝","Img":"wokaijuzhenjinglenvdi.jpg","Author":"隔江","Desc":"宁天来到天玄世界，睁眼竟然是女子的闺房？等等，这是什么情况？身为废物的他，绝美至极的女帝竟要在今晚和他成亲？！震惊！系统激活！震惊他人就能获得奖励！？开局震惊女帝，踏出人生大事第一步，完成举世震惊！\u2026\u2026宁天看向无数天骄圣子：\u201c不是吧\u2026\u2026我就随随便便修炼一下，都能让你们如此震惊？\u201d【简介无能，请移步正文！】[隔江]","CId":96,"CName":"武侠仙侠","LastTime":"2021-05-29 12:33","FirstChapterId":3101682,"LastChapter":"第805章 天玄界主,震惊女帝! 大结局","LastChapterId":3426402,"BookStatus":"完成","SameUserBooks":[{"Id":621010,"Name":"宁天洛无情","Author":"隔江","Img":"ningtianluowuqing.jpg","LastChapterId":4087496,"LastChapter":"第805章 天玄界主,震惊女帝! 大结局","Score":0}],"SameCategoryBooks":[{"Id":622967,"Name":"从诛仙开始的绝世剑仙","Img":"congzhuxiankaishidejueshijianxian.jpg","Score":8},{"Id":625546,"Name":"我在封神开挂","Img":"wozaifengshenkaigua.jpg","Score":8},{"Id":611575,"Name":"天庭时代","Img":"tiantingshidai.jpg","Score":8},{"Id":618608,"Name":"我师侄实在太孝了","Img":"woshizhishizaitaixiaole.jpg","Score":8},{"Id":615629,"Name":"西游之西天送葬团","Img":"xiyouzhixitiansongzangtuan.jpg","Score":8.7},{"Id":624781,"Name":"我在秋斩刑场当缝尸人那些年","Img":"wozaiqiuzhanxingchangdangfengshirenneixienian.jpg","Score":8.4},{"Id":610222,"Name":"清虚世家","Img":"qingxushijia.jpg","Score":8},{"Id":613361,"Name":"武道风流","Img":"wudaofengliu.jpg","Score":8},{"Id":578611,"Name":"开局绑定女武神","Img":"kaijubangdingnvwushen.jpg","Score":7.5},{"Id":613680,"Name":"修仙界最后的单纯","Img":"xiuxianjiezuihoudedanchun.jpg","Score":8.1},{"Id":485327,"Name":"掌门仙路","Img":"zhangmenxianlu.jpg","Score":8},{"Id":624211,"Name":"我夺舍了白衣神王","Img":"woduoshelebaiyishenwang.jpg","Score":8}],"BookVote":{"BookId":608945,"TotalScore":86,"VoterCount":13,"Score":6.6},"UpUser":"152******44","Declare":"该作品由 书友152******44 上传贡献，仅供在线阅读，禁止下载，转载方式传播！"}
     */

    private int status;
    private String info;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * Id : 608945
         * Name : 我开局震惊了女帝
         * Img : wokaijuzhenjinglenvdi.jpg
         * Author : 隔江
         * Desc : 宁天来到天玄世界，睁眼竟然是女子的闺房？等等，这是什么情况？身为废物的他，绝美至极的女帝竟要在今晚和他成亲？！震惊！系统激活！震惊他人就能获得奖励！？开局震惊女帝，踏出人生大事第一步，完成举世震惊！……宁天看向无数天骄圣子：“不是吧……我就随随便便修炼一下，都能让你们如此震惊？”【简介无能，请移步正文！】[隔江]
         * CId : 96
         * CName : 武侠仙侠
         * LastTime : 2021-05-29 12:33
         * FirstChapterId : 3101682
         * LastChapter : 第805章 天玄界主,震惊女帝! 大结局
         * LastChapterId : 3426402
         * BookStatus : 完成
         * SameUserBooks : [{"Id":621010,"Name":"宁天洛无情","Author":"隔江","Img":"ningtianluowuqing.jpg","LastChapterId":4087496,"LastChapter":"第805章 天玄界主,震惊女帝! 大结局","Score":0}]
         * SameCategoryBooks : [{"Id":622967,"Name":"从诛仙开始的绝世剑仙","Img":"congzhuxiankaishidejueshijianxian.jpg","Score":8},{"Id":625546,"Name":"我在封神开挂","Img":"wozaifengshenkaigua.jpg","Score":8},{"Id":611575,"Name":"天庭时代","Img":"tiantingshidai.jpg","Score":8},{"Id":618608,"Name":"我师侄实在太孝了","Img":"woshizhishizaitaixiaole.jpg","Score":8},{"Id":615629,"Name":"西游之西天送葬团","Img":"xiyouzhixitiansongzangtuan.jpg","Score":8.7},{"Id":624781,"Name":"我在秋斩刑场当缝尸人那些年","Img":"wozaiqiuzhanxingchangdangfengshirenneixienian.jpg","Score":8.4},{"Id":610222,"Name":"清虚世家","Img":"qingxushijia.jpg","Score":8},{"Id":613361,"Name":"武道风流","Img":"wudaofengliu.jpg","Score":8},{"Id":578611,"Name":"开局绑定女武神","Img":"kaijubangdingnvwushen.jpg","Score":7.5},{"Id":613680,"Name":"修仙界最后的单纯","Img":"xiuxianjiezuihoudedanchun.jpg","Score":8.1},{"Id":485327,"Name":"掌门仙路","Img":"zhangmenxianlu.jpg","Score":8},{"Id":624211,"Name":"我夺舍了白衣神王","Img":"woduoshelebaiyishenwang.jpg","Score":8}]
         * BookVote : {"BookId":608945,"TotalScore":86,"VoterCount":13,"Score":6.6}
         * UpUser : 152******44
         * Declare : 该作品由 书友152******44 上传贡献，仅供在线阅读，禁止下载，转载方式传播！
         */

        private int Id;
        private String Name;
        private String Img;
        private String Author;
        private String Desc;
        private int CId;
        private String CName;
        private String LastTime;
        private int FirstChapterId;
        private String LastChapter;
        private int LastChapterId;
        private String BookStatus;
        private BookVoteBean BookVote;
        private String UpUser;
        private String Declare;
        private List<SameUserBooksBean> SameUserBooks;
        private List<SameCategoryBooksBean> SameCategoryBooks;

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getImg() {
            return Img;
        }

        public void setImg(String img) {
            Img = img;
        }

        public String getAuthor() {
            return Author;
        }

        public void setAuthor(String author) {
            Author = author;
        }

        public String getDesc() {
            return Desc;
        }

        public void setDesc(String desc) {
            Desc = desc;
        }

        public int getCId() {
            return CId;
        }

        public void setCId(int CId) {
            this.CId = CId;
        }

        public String getCName() {
            return CName;
        }

        public void setCName(String CName) {
            this.CName = CName;
        }

        public String getLastTime() {
            return LastTime;
        }

        public void setLastTime(String lastTime) {
            LastTime = lastTime;
        }

        public int getFirstChapterId() {
            return FirstChapterId;
        }

        public void setFirstChapterId(int firstChapterId) {
            FirstChapterId = firstChapterId;
        }

        public String getLastChapter() {
            return LastChapter;
        }

        public void setLastChapter(String lastChapter) {
            LastChapter = lastChapter;
        }

        public int getLastChapterId() {
            return LastChapterId;
        }

        public void setLastChapterId(int lastChapterId) {
            LastChapterId = lastChapterId;
        }

        public String getBookStatus() {
            return BookStatus;
        }

        public void setBookStatus(String bookStatus) {
            BookStatus = bookStatus;
        }

        public BookVoteBean getBookVote() {
            return BookVote;
        }

        public void setBookVote(BookVoteBean bookVote) {
            BookVote = bookVote;
        }

        public String getUpUser() {
            return UpUser;
        }

        public void setUpUser(String upUser) {
            UpUser = upUser;
        }

        public String getDeclare() {
            return Declare;
        }

        public void setDeclare(String declare) {
            Declare = declare;
        }

        public List<SameUserBooksBean> getSameUserBooks() {
            return SameUserBooks;
        }

        public void setSameUserBooks(List<SameUserBooksBean> sameUserBooks) {
            SameUserBooks = sameUserBooks;
        }

        public List<SameCategoryBooksBean> getSameCategoryBooks() {
            return SameCategoryBooks;
        }

        public void setSameCategoryBooks(List<SameCategoryBooksBean> sameCategoryBooks) {
            SameCategoryBooks = sameCategoryBooks;
        }


        public static class BookVoteBean implements Serializable {
            /**
             * BookId : 608945
             * TotalScore : 86
             * VoterCount : 13
             * Score : 6.6
             */

            private int BookId;
            private int TotalScore;
            private int VoterCount;
            private double Score;

            public int getBookId() {
                return BookId;
            }

            public void setBookId(int bookId) {
                BookId = bookId;
            }

            public int getTotalScore() {
                return TotalScore;
            }

            public void setTotalScore(int totalScore) {
                TotalScore = totalScore;
            }

            public int getVoterCount() {
                return VoterCount;
            }

            public void setVoterCount(int voterCount) {
                VoterCount = voterCount;
            }

            public double getScore() {
                return Score;
            }

            public void setScore(double score) {
                Score = score;
            }
        }


        public static class SameUserBooksBean implements Serializable {
            /**
             * Id : 621010
             * Name : 宁天洛无情
             * Author : 隔江
             * Img : ningtianluowuqing.jpg
             * LastChapterId : 4087496
             * LastChapter : 第805章 天玄界主,震惊女帝! 大结局
             * Score : 0
             */

            private int Id;
            private String Name;
            private String Author;
            private String Img;
            private int LastChapterId;
            private String LastChapter;
            private double Score;

            public int getId() {
                return Id;
            }

            public void setId(int id) {
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

            public int getLastChapterId() {
                return LastChapterId;
            }

            public void setLastChapterId(int lastChapterId) {
                LastChapterId = lastChapterId;
            }

            public String getLastChapter() {
                return LastChapter;
            }

            public void setLastChapter(String lastChapter) {
                LastChapter = lastChapter;
            }

            public double getScore() {
                return Score;
            }

            public void setScore(double score) {
                Score = score;
            }
        }


        public static class SameCategoryBooksBean implements Serializable {
            /**
             * Id : 622967
             * Name : 从诛仙开始的绝世剑仙
             * Img : congzhuxiankaishidejueshijianxian.jpg
             * Score : 8
             */

            private int Id;
            private String Name;
            private String Img;
            private double Score;

            public int getId() {
                return Id;
            }

            public void setId(int id) {
                Id = id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getImg() {
                return Img;
            }

            public void setImg(String img) {
                Img = img;
            }

            public double getScore() {
                return Score;
            }

            public void setScore(double score) {
                Score = score;
            }
        }
    }
}
