package com.example.mybookactivity.ui.showbookcontent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.mybookactivity.MyApplicaton;
import com.example.mybookactivity.R;
import com.example.mybookactivity.databinding.BookContentwindowLayoutBinding;
import com.example.mybookactivity.databinding.PopWindowLayoutBinding;
import com.example.mybookactivity.logic.model.BookIndex;
import com.example.mybookactivity.logic.model.BookPassage;
import com.example.mybookactivity.ui.bookdetails.BookDetailsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShowBookContentDialog extends Dialog {
    private Context context;
    private BookContentwindowLayoutBinding binding;
    private BookPassage bookPassage;
    private int id2;
    private LinearLayout bgview;
    private String bookUrl;
    private String bookAuthor;
    private TextView showBookContent;
    private int textSize=20;


    private boolean isDay = true;
    public ShowBookContentDialog(@NonNull Context context) {
        super(context);
        this.context=context;
    }

    public ShowBookContentDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context=context;
    }
    public ShowBookContentDialog(@NonNull Context context, int themeResId, BookPassage bookPassage, int id2, LinearLayout bgview,String bookUrl,String bookAuthor,TextView showBookCentent) {
        super(context, themeResId);
        this.context=context;
        this.bookPassage = bookPassage;
        this.id2 = id2;
        this.bgview = bgview;
        this.bookUrl = bookUrl;
        this.bookAuthor = bookAuthor;
        this.showBookContent = showBookCentent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = BookContentwindowLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Glide.with(context).load(bookUrl).into(binding.bookCOntentPic);
        binding.bookContentWindowAuthor.setText(bookAuthor);
        binding.bookContentWindowName.setText(bookPassage.getData().getName());
        binding.bookContentFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDay){
                    isDay=false;
                    binding.bookContentFloatBtn.setImageResource(R.drawable.night);
                    bgview.setBackgroundColor(Color.rgb(19,19,19));
                    TextView textView = bgview.findViewById(R.id.showBookContent);
                    textView.setTextColor(Color.rgb(93,93,93));
                    TextView textView1 = bgview.findViewById(R.id.showBookName);
                    textView1.setTextColor(Color.rgb(93,93,93));
                }
                else {
                    isDay=true;
                    binding.bookContentFloatBtn.setImageResource(R.drawable.sunny);
                    bgview.setBackgroundResource(R.drawable.readbookbg);
                    TextView textView = bgview.findViewById(R.id.showBookContent);
                    textView.setTextColor(Color.BLACK);
                    TextView textView1 = bgview.findViewById(R.id.showBookName);
                    textView1.setTextColor(Color.BLACK);
                }
            }
        });
        binding.bigTextSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textSize<=40){
                    textSize =textSize+2;
                    showBookContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
                }

            }
        });
        binding.smallTextSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textSize>=10){
                    textSize =textSize-2;
                    showBookContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
                }

            }
        });
        binding.bookContentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity)context;
                activity.finish();
            }
        });
        binding.bookContentCur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity)context;
                activity.finish();
            }
        });
        binding.bookContentLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowBookContentActivity.actionStartBookContentActivity(context,id2,bookPassage.getData().getPid(),bookUrl,bookAuthor);
                Activity activity = (Activity)context;
                activity.finish();
            }
        });
        binding.bookContentNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowBookContentActivity.actionStartBookContentActivity(context,id2,bookPassage.getData().getNid(),bookUrl,bookAuthor);
                Activity activity = (Activity)context;
                activity.finish();
            }
        });

    }
}
