package com.example.mybookactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mybookactivity.databinding.ActivityAddBookBinding;
import com.example.mybookactivity.logic.AllMyNetWork;
import com.example.mybookactivity.logic.Repository;
import com.example.mybookactivity.logic.dao.BookDao;
import com.example.mybookactivity.logic.dao.entity.Book;
import com.example.mybookactivity.logic.model.BookInfo;
import com.example.mybookactivity.logic.model.BookSearch;
import com.example.mybookactivity.logic.network.BookContentServiceCreator;
import com.example.mybookactivity.logic.network.BookInfoService;
import com.example.mybookactivity.logic.network.BookInfoServiceCreator;
import com.example.mybookactivity.logic.network.SearchBookService;
import com.example.mybookactivity.logic.network.SearchBookServiceCreator;
import com.example.mybookactivity.utils.GetBookIdUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookActivity extends AppCompatActivity {


    public ActivityAddBookBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);


        binding.searchBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.showSearchBookInfo.setText("");
                progressDialog.show();
                SearchBookService searchBookService =SearchBookServiceCreator.getInstance().create(SearchBookService.class);
                String name = binding.searchBookEt.getText().toString();
                searchBookService.getSearchBook(name,1,"app2").enqueue(new Callback<BookSearch>() {
                    @Override
                    public void onResponse(Call<BookSearch> call, Response<BookSearch> response) {
                        BookSearch bookSearch = response.body();
                        if(bookSearch.getInfo().equals("success")){
                            StringBuilder stringBuilder = new StringBuilder();
                            List<BookSearch.DataBean> list =  bookSearch.getData();
                            for(int i=0;i<list.size();i++){
                                stringBuilder.append("书名："+list.get(i).getName()+"\t");
                                stringBuilder.append("id：" +list.get(i).getId()+"\n");
                            }
                            binding.showSearchBookInfo.setText(stringBuilder.toString());
                            progressDialog.dismiss();
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(MyApplicaton.context,"失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BookSearch> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(MyApplicaton.context,"失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        binding.addBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();


                int id =248872;
                id = Integer.parseInt(binding.addBookEt.getText().toString());
                BookInfoServiceCreator serviceCreator = BookInfoServiceCreator.getInstance();
                BookInfoService bookInfoService = serviceCreator.create(BookInfoService.class);
                bookInfoService.getBookInfo(GetBookIdUtil.getBookId(id),id).enqueue(new Callback<BookInfo>() {
                    @Override
                    public void onResponse(Call<BookInfo> call, Response<BookInfo> response) {
                        BookInfo bookInfo = response.body();
                        if(bookInfo!=null && bookInfo.getInfo().equals("success")){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                   boolean result =  BookDao.insertBook(bookInfo);
                                   runOnUiThread(new Runnable() {
                                       @Override
                                       public void run() {
                                           if(result){
                                               Toast.makeText(AddBookActivity.this,"成功",Toast.LENGTH_SHORT).show();
                                           }
                                           else  {Toast.makeText(AddBookActivity.this,"失败",Toast.LENGTH_SHORT).show();}
                                           progressDialog.dismiss();
                                       }
                                   });
                                }
                            }).start();

                        }
                        else {
                            Toast.makeText(AddBookActivity.this,"查无此数据",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                        System.out.println("nnnnn"+bookInfo.getStatus()+bookInfo.getInfo()+bookInfo.getData().getName()+bookInfo.getData().getBookVote().getTotalScore());

                    }

                    @Override
                    public void onFailure(Call<BookInfo> call, Throwable t) {
                        Toast.makeText(AddBookActivity.this,"失败",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}