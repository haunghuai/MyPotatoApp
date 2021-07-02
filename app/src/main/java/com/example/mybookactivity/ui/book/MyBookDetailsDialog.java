package com.example.mybookactivity.ui.book;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.mybookactivity.MyApplicaton;
import com.example.mybookactivity.logic.dao.entity.BookShelf;
import com.example.mybookactivity.ui.bookdetails.BookDetailsActivity;
import com.example.mybookactivity.databinding.PopWindowLayoutBinding;
import com.example.mybookactivity.ui.chapter.ChapterActivity;

public class MyBookDetailsDialog extends Dialog {

    private PopWindowLayoutBinding binding;
    private Context mContext;
    private BookShelf bookShelf;
    public MyBookDetailsDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public MyBookDetailsDialog(@NonNull Context context, int themeResId, BookShelf bookShelf) {
        super(context, themeResId);
        this.mContext = context;
        this.bookShelf = bookShelf;
    }

    protected MyBookDetailsDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PopWindowLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.myPopWindowDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDetailsActivity.actionShelfStart(mContext,bookShelf);
            }
        });

        binding.myPopWindowChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChapterActivity.actionStartChapterActivity(mContext,bookShelf.getId2(),MyApplicaton.url+bookShelf.getBookPic(),bookShelf.getAuthor());
            }
        });
        binding.myPopWindowAuthor.setText(bookShelf.getAuthor());
        binding.myPopWindowName.setText(bookShelf.getBookName());
        Glide.with(mContext).load(MyApplicaton.url+bookShelf.getBookPic()).into(binding.myPopWindowPic);
    }

}
