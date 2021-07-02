package com.example.mybookactivity.logic.dao;


import android.util.Log;
import android.widget.Toast;

import com.example.mybookactivity.MyApplicaton;
import com.example.mybookactivity.logic.dao.entity.Book;
import com.example.mybookactivity.logic.dao.entity.BookChapter;
import com.example.mybookactivity.logic.dao.entity.BookContent;
import com.example.mybookactivity.logic.dao.entity.BookShelf;
import com.example.mybookactivity.logic.model.BookIndex;
import com.example.mybookactivity.logic.model.BookInfo;
import com.example.mybookactivity.logic.model.BookPassage;
import com.example.mybookactivity.utils.GetBookIdUtil;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookDao {
    public static String basePicUrl = "https://imgapixs.pinzhu1688.com/BookFiles/BookImages/";
    public static boolean insertBook(BookInfo bookInfo){
        boolean result = true;
//        List<Book> list = LitePal.where("id2=?", String.valueOf(bookInfo.getData().getId())).find(Book.class);
//        if(list!=null){
//
//        }
//        else {
        Book book = new Book();
        book.setFirstChapterId(bookInfo.getData().getFirstChapterId());
        book.setScore(bookInfo.getData().getBookVote().getScore());
        book.setLastTime(bookInfo.getData().getLastTime());
        book.setAuthor(bookInfo.getData().getAuthor());
        book.setId2(bookInfo.getData().getId());
        book.setId1(GetBookIdUtil.getBookId(bookInfo.getData().getId()));
        book.setcName(bookInfo.getData().getCName());
        book.setLastChapterId(bookInfo.getData().getLastChapterId());
        book.setBookName(bookInfo.getData().getName());
        book.setDesc(bookInfo.getData().getDesc());
        book.setBookStatus(bookInfo.getData().getBookStatus());
        book.setBookPic(bookInfo.getData().getImg());
        result = book.save();
//        }
        return result;
    }

    public static boolean insertBookShelf(BookInfo bookInfo){
        boolean result=false;
        BookShelf bookShelf = new BookShelf();
        bookShelf.setFirstChapterId(bookInfo.getData().getFirstChapterId());
        bookShelf.setScore(bookInfo.getData().getBookVote().getScore());
        bookShelf.setLastTime(bookInfo.getData().getLastTime());
        bookShelf.setAuthor(bookInfo.getData().getAuthor());
        bookShelf.setId2(bookInfo.getData().getId());
        bookShelf.setId1(GetBookIdUtil.getBookId(bookInfo.getData().getId()));
        bookShelf.setcName(bookInfo.getData().getCName());
        bookShelf.setLastChapterId(bookInfo.getData().getLastChapterId());
        bookShelf.setBookName(bookInfo.getData().getName());
        bookShelf.setDesc(bookInfo.getData().getDesc());
        bookShelf.setBookStatus(bookInfo.getData().getBookStatus());
        bookShelf.setBookPic(bookInfo.getData().getImg());
        bookShelf.setLastChapter(bookInfo.getData().getLastChapter());
        bookShelf.setCurrentChapter(bookInfo.getData().getFirstChapterId());
        result = bookShelf.save();
        return result;

    }



    public static int deleteBookShelf(int id2){
       int result = LitePal.deleteAll(BookShelf.class,"id2=?",String.valueOf(id2));
       return result;
    }
    public static int isHasBookShelf(int id2){
        List<BookShelf> list = LitePal.where("id2=?",String.valueOf(id2)).find(BookShelf.class);
        if(list.size()==0){
            return 0;
        }
        else {
            return 1;
        }
    }


    public static List<BookShelf> selectBookShelf(){
        List<BookShelf> list = LitePal.findAll(BookShelf.class);
        return list;
    }

    public static boolean insertBookChapter(BookIndex bookIndex){
        boolean result = true;

        int volumeSize = bookIndex.getData().getList().size();
        String name = bookIndex.getData().getName();
        int id2 = bookIndex.getData().getId();
        int id1 = GetBookIdUtil.getBookId(id2);
        for(int i=0;i<volumeSize;i++){
            BookIndex.DataBean.ListBeanX a = bookIndex.getData().getList().get(i);
            int chapterSize = a.getList().size();
            for(int j=0;j<chapterSize;j++){
                BookIndex.DataBean.ListBeanX.ListBean b = a.getList().get(j);
                BookChapter bookChapter = new BookChapter();
                bookChapter.setBookVolume(a.getName());
                bookChapter.setId2(id2);
                bookChapter.setId1(id1);
                bookChapter.setId3(b.getId());
                bookChapter.setBookChapterName(b.getName());
                bookChapter.setBookName(name);
                result = bookChapter.save() & true;
            }
        }

        return result;
    }
    public static boolean insertBookContent(BookPassage bookPassage){
        BookContent bookContent = new BookContent();
        bookContent.setId2(bookPassage.getData().getId());
        bookContent.setId1(GetBookIdUtil.getBookId(bookPassage.getData().getId()));
        bookContent.setId3(bookPassage.getData().getCid());
        bookContent.setcName(bookPassage.getData().getCname());
        bookContent.setNid(bookPassage.getData().getNid());
        bookContent.setPid(bookPassage.getData().getPid());
        bookContent.setHasContent(bookPassage.getData().getHasContent());
        bookContent.setContent(bookPassage.getData().getContent());
        boolean result = bookContent.save();
        return result;
    }

    public static List<Book> getRecommandBook(){
        List<Book> bookList = LitePal.findAll(Book.class);

        return bookList;
    }
    public static List<Book> getHotRefreshBook(){
        List<Book> bookList = LitePal.where("bookstatus=?",String.valueOf("连载")).find(Book.class);
        return bookList;
    }

    public static List<Book> getCompleteBook(){
        List<Book> all = new ArrayList<>();
        List<Book> bookList = LitePal.where("bookstatus=?",String.valueOf("完结")).find(Book.class);
        List<Book> bookList1 = LitePal.where("bookstatus=?",String.valueOf("完成")).find(Book.class);
        all.addAll(bookList);
        all.addAll(bookList1);
        return all;
    }
}
