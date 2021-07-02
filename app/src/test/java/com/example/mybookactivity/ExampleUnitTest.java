package com.example.mybookactivity;

import android.util.Log;

import com.example.mybookactivity.logic.dao.BookDao;
import com.example.mybookactivity.logic.dao.entity.Book;
import com.example.mybookactivity.logic.model.BookPassage;
import com.example.mybookactivity.logic.model.BookIndex;
import com.example.mybookactivity.logic.model.BookInfo;
import com.example.mybookactivity.logic.model.BookSearch;
import com.example.mybookactivity.logic.network.BookContentService;
import com.example.mybookactivity.logic.network.BookContentServiceCreator;
import com.example.mybookactivity.logic.network.BookIndexService;
import com.example.mybookactivity.logic.network.BookIndexServiceCreator;
import com.example.mybookactivity.logic.network.BookInfoService;
import com.example.mybookactivity.logic.network.BookInfoServiceCreator;
import com.example.mybookactivity.logic.network.SearchBookService;
import com.example.mybookactivity.logic.network.SearchBookServiceCreator;
import com.example.mybookactivity.ui.bookdetails.BookDetailsActivity;
import com.google.gson.Gson;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    private static final String TAG = "my111";
    @Test
    public void getBookInfo(){

        System.out.println("11");
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://infosxs.pinzhu1688.com/")
//                                                    .addConverterFactory(GsonConverterFactory.create()).build();
//        BookInfoService bookInfoService = retrofit.create(BookInfoService.class);
//        bookInfoService.getBookInfo(609,608945).enqueue(new Callback<BookInfo>() {
//            @Override
//            public void onResponse(Call<BookInfo> call, Response<BookInfo> response) {
//                BookInfo bookInfo = response.body();
//
//                System.out.println("nnnnn"+bookInfo.getStatus()+bookInfo.getInfo()+bookInfo.getData().getName());
//
//            }
//
//            @Override
//            public void onFailure(Call<BookInfo> call, Throwable t) {
//                System.out.println("nnnnnmmmm"+t.toString());
//            }
//        });

        BookInfoServiceCreator serviceCreator = BookInfoServiceCreator.getInstance();
        BookInfoService bookInfoService = serviceCreator.create(BookInfoService.class);
        bookInfoService.getBookInfo(249,248872).enqueue(new Callback<BookInfo>() {
            @Override
            public void onResponse(Call<BookInfo> call, Response<BookInfo> response) {
                BookInfo bookInfo = response.body();
                System.out.println("nnnnn"+bookInfo.getStatus()+bookInfo.getInfo()+bookInfo.getData().getName()+bookInfo.getData().getImg());

            }

            @Override
            public void onFailure(Call<BookInfo> call, Throwable t) {
                System.out.println("nnnmmmmm");
                Log.e(TAG, "onFailure: "+t.toString() );
            }
        });


        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//    String json = response.body().toString();
//    json = json.replace("},]}", "}]}");
//    json= json.replace("},]}", "}]}");
//    Gson gson=new Gson();
//    BookIndex bookIndex = gson.fromJson(json, BookIndex.class);
//                System.out.println(bookIndex.getData().getId()+bookIndex.getData().getName());
    @Test
    public void getBookVlume(){
        BookIndexServiceCreator creator = BookIndexServiceCreator.getInstance();
        BookIndexService bookIndexService = creator.create(BookIndexService.class);

        bookIndexService.getBookIndex(609,608945).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                        String json = response.body().string();
                        json = json.replace("},]}", "}]}");
                        json= json.replace("},]}", "}]}");
                        Gson gson=new Gson();
                        BookIndex bookIndex = gson.fromJson(json, BookIndex.class);
                System.out.println(bookIndex.getData().getId()+bookIndex.getData().getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getBookContent(){
        BookContentServiceCreator creator = BookContentServiceCreator.getInstance();
        BookContentService bookContentService = creator.create(BookContentService.class);
        bookContentService.getBookContent(609,608945,3101682).enqueue(new Callback<BookPassage>() {
            @Override
            public void onResponse(Call<BookPassage> call, Response<BookPassage> response) {
                BookPassage bookContent = response.body();

                System.out.println(bookContent.getData().getContent());
            }

            @Override
            public void onFailure(Call<BookPassage> call, Throwable t) {
                System.out.println("aaaaa");
            }
        });
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSearchBook(){
        SearchBookServiceCreator creator = SearchBookServiceCreator.getInstance();
        SearchBookService searchBookService = creator.create(SearchBookService.class);
        searchBookService.getSearchBook("诡秘",1,"app2").enqueue(new Callback<BookSearch>() {
            @Override
            public void onResponse(Call<BookSearch> call, Response<BookSearch> response) {
                BookSearch bookSearch = response.body();
                List<BookSearch.DataBean> list =  bookSearch.getData();
                for(int i=0;i<list.size();i++){
                    System.out.println(list.get(i).getAuthor());
                }
            }

            @Override
            public void onFailure(Call<BookSearch> call, Throwable t) {
                System.out.println("failure");
            }
        });
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getHotRefresh(){
        List<Book> hotRefreshBook = BookDao.getHotRefreshBook();
        System.out.println(hotRefreshBook.get(0).getBookName()+hotRefreshBook.get(1).getBookName());

        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}