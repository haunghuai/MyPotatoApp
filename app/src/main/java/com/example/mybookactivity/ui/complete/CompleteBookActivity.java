package com.example.mybookactivity.ui.complete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mybookactivity.ActivityCollector;
import com.example.mybookactivity.databinding.ActivityCompleteBookBinding;
import com.example.mybookactivity.logic.dao.BookDao;
import com.example.mybookactivity.logic.dao.entity.Book;

import java.util.List;

public class CompleteBookActivity extends AppCompatActivity {


    private ActivityCompleteBookBinding binding;
    private String type;
    private List<Book> books;
    private CompleteBookAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.getInstance().addActivity(this);
        binding = ActivityCompleteBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        type=getIntent().getStringExtra("type");
        switch (type){
            case "推荐":
                books = BookDao.getRecommandBook();
                break;
            case "热门连载":
                books =BookDao.getHotRefreshBook();
                break;
            case "完本精选":
                books = BookDao.getCompleteBook();
                break;
        }
        binding.myCompleteTitle.setText(type);
        adapter = new CompleteBookAdapter(this,books);
        binding.maCompleteBookRV.setLayoutManager(new LinearLayoutManager(this));
        binding.maCompleteBookRV.setAdapter(adapter);


        binding.meCompleteBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
    }

    public static void actionStartCompleteActivity(Context context, String type){
        Intent intent = new Intent(context,CompleteBookActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);

    }
}