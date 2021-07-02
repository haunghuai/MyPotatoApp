package com.example.mybookactivity;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.example.mybookactivity.logic.dao.BookDao;
import com.example.mybookactivity.logic.model.BookInfo;
import com.example.mybookactivity.logic.network.BookInfoService;
import com.example.mybookactivity.logic.network.BookInfoServiceCreator;
import com.example.mybookactivity.utils.GetBookIdUtil;

import org.litepal.LitePal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyApplicaton extends Application {
    public static String url="https://imgapixs.pinzhu1688.com/BookFiles/BookImages/";
    public static Context context;
    public static boolean isFirstCreate=false;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(this);
       isFirstCreate = true;



    }
}
