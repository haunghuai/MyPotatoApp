package com.example.mybookactivity.logic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mybookactivity.logic.dao.BookDao;
import com.example.mybookactivity.logic.dao.entity.Book;
import com.example.mybookactivity.logic.dao.entity.BookShelf;
import com.example.mybookactivity.logic.model.BookIndex;
import com.example.mybookactivity.logic.model.BookInfo;
import com.example.mybookactivity.logic.model.BookPassage;
import com.example.mybookactivity.logic.model.BookSearch;
import com.example.mybookactivity.utils.GetBookIdUtil;

import org.litepal.LitePal;

import java.util.List;

public class Repository {
    public Repository(){}

    public static Repository getInstance(){
        return RepositoryHolder.sInstance;
    }
    private static class RepositoryHolder{
        private static final Repository sInstance = new Repository();
    }


    public boolean insertIntoBookShelf(int id2){
        AllMyNetWork.getInstance().getBookInfo(id2);
        AllMyNetWork.getInstance().setCallBackInfo(new AllMyNetWork.GetCallBackInfo() {
            @Override
            public void getCallBackInfo(BookInfo bookInfo) {
                BookDao.insertBookShelf(bookInfo);
            }
        });
        return true;

    }

    public MutableLiveData<List<BookShelf>> selectBookShelf(){
        MutableLiveData<List<BookShelf>> listMutableLiveData = new MutableLiveData<>();
        listMutableLiveData.setValue(BookDao.selectBookShelf());
        return listMutableLiveData;
    }

    public MutableLiveData<Book> getBookInfo(int id2){

        MutableLiveData<Book> bookInfoLiveData = new MutableLiveData<>();

        List<Book> list= LitePal.where("id2=?",String.valueOf(id2)).find(Book.class);
        if(list!=null){
            bookInfoLiveData.setValue(list.get(0));

        }
        else {
            AllMyNetWork.getInstance().getBookInfo(id2);
            AllMyNetWork.getInstance().setCallBackInfo(new AllMyNetWork.GetCallBackInfo() {
                @Override
                public void getCallBackInfo(BookInfo bookInfo) {
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
                    bookInfoLiveData.setValue(book);

                }
            });
        }

        return bookInfoLiveData;
    }


    public MutableLiveData<BookSearch> getBookSearch(String bookName){
        MutableLiveData<BookSearch> bookSearchMutableLiveData = new MutableLiveData<>();
        AllMyNetWork.getInstance().getBookSearch(bookName);
        AllMyNetWork.getInstance().setCallBackBookSearch(new AllMyNetWork.GetCallBackBookSearch() {
            @Override
            public void callBackBookSearch(BookSearch bookSearch) {
                bookSearchMutableLiveData.setValue(bookSearch);
            }
        });
        return bookSearchMutableLiveData;

    }

    public MutableLiveData<List<Book>> getRecommandBook(){
        MutableLiveData<List<Book>> liveData = new MutableLiveData<>();
        liveData.setValue(BookDao.getRecommandBook());
        return liveData;
    }

    public MutableLiveData<List<Book>> getHotRefreshBook(){
        MutableLiveData<List<Book>> liveData = new MutableLiveData<>();
        liveData.setValue(BookDao.getHotRefreshBook());
        return liveData;
    }
    public MutableLiveData<List<Book>> getCompleteBook(){
        MutableLiveData<List<Book>> liveData = new MutableLiveData<>();
        liveData.setValue(BookDao.getCompleteBook());
        return liveData;
    }

    public MutableLiveData<BookIndex> getBookIndex(int id){
        MutableLiveData<BookIndex> liveData = new MutableLiveData<>();
        AllMyNetWork.getInstance().getBookIndex(id);
        AllMyNetWork.getInstance().setCallBackBookIndex(new AllMyNetWork.GetCallBackBookIndex() {
            @Override
            public void callBackBookIndex(BookIndex bookIndex) {
                liveData.setValue(bookIndex);
            }
        });
        return liveData;
    }
    public MutableLiveData<BookPassage> getBookPassage(int id2,int id3){
        MutableLiveData<BookPassage> liveData = new MutableLiveData<>();
        AllMyNetWork.getInstance().getBookPassage(id2,id3);
        AllMyNetWork.getInstance().setCallBackBookPassage(new AllMyNetWork.GetCallBackBookPassage() {
            @Override
            public void callBackBookPassage(BookPassage bookPassage) {
                liveData.setValue(bookPassage);
            }
        });
        return liveData;
    }

}
