package com.example.mybookactivity.ui.recommand;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.mybookactivity.ui.recommand.OnViewPagerTouchListen;

public class MyAutoViewPager extends ViewPager {

    private OnViewPagerTouchListen onViewPagerTouchListen=null;


    public MyAutoViewPager(@NonNull Context context) {
        super(context);
    }

    public MyAutoViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public void setOnViewPagerTouchListen(OnViewPagerTouchListen o){

            this.onViewPagerTouchListen = o;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (onViewPagerTouchListen != null) {
                    onViewPagerTouchListen.onViewPagerTouch(true);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (onViewPagerTouchListen != null) {
                    onViewPagerTouchListen.onViewPagerTouch(false);
                }
                break;
        }

        return super.onTouchEvent(ev);
    }
}
