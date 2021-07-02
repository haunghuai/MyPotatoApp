package com.example.mybookactivity.ui.recommand;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.mybookactivity.MyApplicaton;
import com.example.mybookactivity.logic.dao.entity.Book;
import com.example.mybookactivity.ui.bookdetails.BookDetailsActivity;

import java.util.List;

public class MyAutoViewpagerAdapter extends PagerAdapter {
    Context mContext;
    List<Book> mList;

    public MyAutoViewpagerAdapter(Context context,List<Book> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView imageView = new ImageView(mContext);
        int realPosition = position % mList.size();
        Book book = mList.get(realPosition);
        Glide.with(mContext).load(MyApplicaton.url+book.getBookPic()).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDetailsActivity.actionBookStart(mContext,book);
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {

        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    public int getRealSize(){
        return mList.size();
    }
}
