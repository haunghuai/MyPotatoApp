package com.example.mybookactivity.logic;

import android.util.Log;
import android.widget.Toast;

import com.example.mybookactivity.MyApplicaton;
import com.example.mybookactivity.logic.model.BookIndex;
import com.example.mybookactivity.logic.model.BookInfo;
import com.example.mybookactivity.logic.model.BookPassage;
import com.example.mybookactivity.logic.model.BookSearch;
import com.example.mybookactivity.logic.network.BookContentService;
import com.example.mybookactivity.logic.network.BookContentServiceCreator;
import com.example.mybookactivity.logic.network.BookIndexService;
import com.example.mybookactivity.logic.network.BookIndexServiceCreator;
import com.example.mybookactivity.logic.network.BookInfoService;
import com.example.mybookactivity.logic.network.BookInfoServiceCreator;
import com.example.mybookactivity.logic.network.SearchBookService;
import com.example.mybookactivity.logic.network.SearchBookServiceCreator;
import com.example.mybookactivity.utils.GetBookIdUtil;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllMyNetWork {
    public AllMyNetWork(){}

    public static AllMyNetWork getInstance(){

        return AllMyNetWorkHolder.sInstance;
    }
    private static class AllMyNetWorkHolder{
        private static final AllMyNetWork sInstance = new AllMyNetWork();
    }



    private  BookContentService bookContentService = BookContentServiceCreator.getInstance().create(BookContentService.class);
    private  BookIndexService bookIndexService = BookIndexServiceCreator.getInstance().create(BookIndexService.class);
    private  BookInfoService bookInfoService = BookInfoServiceCreator.getInstance().create(BookInfoService.class);
    private SearchBookService searchBookService = SearchBookServiceCreator.getInstance().create(SearchBookService.class);

    public GetCallBackInfo callBackInfo=null;
    public void setCallBackInfo(GetCallBackInfo info){
        callBackInfo=info;
    }
    public  void getBookInfo(int id2){
        bookInfoService.getBookInfo(GetBookIdUtil.getBookId(id2),id2).enqueue(new Callback<BookInfo>() {
            @Override
            public void onResponse(Call<BookInfo> call, Response<BookInfo> response) {
                BookInfo bookInfo = response.body();
                if(bookInfo!=null && callBackInfo!=null){
                    callBackInfo.getCallBackInfo(bookInfo);
                }
                else {
                    throw new RuntimeException("请求数据失败");
                }
            }

            @Override
            public void onFailure(Call<BookInfo> call, Throwable t) {

                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });


    }
    interface GetCallBackInfo{
        void getCallBackInfo(BookInfo bookInfo);
    }


    public GetCallBackBookSearch callBackBookSearch=null;
    public void setCallBackBookSearch(GetCallBackBookSearch search){ callBackBookSearch=search;}
    interface GetCallBackBookSearch{
        void callBackBookSearch(BookSearch bookSearch);
    }
    public void getBookSearch(String bookName){
        searchBookService.getSearchBook(bookName,1,"app2").enqueue(new Callback<BookSearch>() {
            @Override
            public void onResponse(Call<BookSearch> call, Response<BookSearch> response) {
                BookSearch bookSearch = response.body();
                if(bookSearch!=null && callBackBookSearch!=null){
                    callBackBookSearch.callBackBookSearch(bookSearch);
                }
                else {throw new RuntimeException("请求数据失败");}
            }

            @Override
            public void onFailure(Call<BookSearch> call, Throwable t) {
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });

    }
    public GetCallBackBookIndex callBackBookIndex=null;
    public void setCallBackBookIndex(GetCallBackBookIndex index){ callBackBookIndex=index;}
    interface GetCallBackBookIndex{
        void callBackBookIndex(BookIndex bookIndex);
    }
    public void getBookIndex(int id){
        bookIndexService.getBookIndex(GetBookIdUtil.getBookId(id),id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response!=null && callBackBookIndex!=null){
                    try {
                        String json = response.body().string();
                        json = json.replace("},]}", "}]}");
                        json= json.replace("},]}", "}]}");
                        Gson gson=new Gson();
                        BookIndex bookIndex = gson.fromJson(json, BookIndex.class);
                        callBackBookIndex.callBackBookIndex(bookIndex);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    throw new RuntimeException("请求数据失败");
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });

    }


    public GetCallBackBookPassage callBackBookPassage=null;
    public void setCallBackBookPassage(GetCallBackBookPassage passage){ callBackBookPassage=passage;}
    interface GetCallBackBookPassage{
        void callBackBookPassage(BookPassage bookPassage);
    }
    public void getBookPassage(int id2,int id3){
      bookContentService.getBookContent(GetBookIdUtil.getBookId(id2),id2,id3).enqueue(new Callback<BookPassage>() {
          @Override
          public void onResponse(Call<BookPassage> call, Response<BookPassage> response) {
              BookPassage bookPassage = response.body();
              if(bookPassage!=null && callBackBookPassage!=null){
                  callBackBookPassage.callBackBookPassage(bookPassage);
              }
              else {throw new RuntimeException("请求数据失败");}
          }

          @Override
          public void onFailure(Call<BookPassage> call, Throwable t) {

          }
      });

    }


}
