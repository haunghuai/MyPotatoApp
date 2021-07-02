package com.example.mybookactivity.ui.recommand;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.mybookactivity.MyApplicaton;
import com.example.mybookactivity.R;
import com.example.mybookactivity.databinding.RecommandfragmentBinding;
import com.example.mybookactivity.logic.dao.entity.Book;
import com.example.mybookactivity.logic.model.BookInfo;
import com.example.mybookactivity.ui.bookdetails.BookDetailsActivity;
import com.example.mybookactivity.ui.complete.CompleteBookActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecommandFragment extends Fragment {
    private static final String TAG = "RecommandFragment";

    MyAutoViewpagerAdapter adapter;

    boolean istouch;


    private Handler mHandler;
    private View view;

    private boolean isCreate=false;

    private HotRefreshAdapter hotRefreshAdapter;

    private CompleteBookAdapter completeAdapter;


    private RecommandfragmentBinding binding;

    private RecommandFragmentViewModel viewModel;


    private Book hotBook; //记录热门连载的首头book
    private Book completeBook; //记录完本精选的book
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(getActivity()).get(RecommandFragmentViewModel.class);




        mHandler = new Handler();

        binding = RecommandfragmentBinding.inflate(inflater,container,false);
        view = binding.getRoot();

        initViewPager(view);
        initHotRecyclerView(view);

        initCompleteRecyclerView(view);

        binding.hotRefreshTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDetailsActivity.actionBookStart(getContext(),hotBook);
            }
        });
        binding.completeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDetailsActivity.actionBookStart(getContext(),completeBook);
            }
        });

        return view;
    }
    private void initViewPager(View view){

        binding.autoViewpager.setOnViewPagerTouchListen(new OnViewPagerTouchListen() {
            @Override
            public void onViewPagerTouch(boolean isTouch) {
                istouch=isTouch;
            }
        });
        viewModel.refreshViewPager();
        viewModel.viewpagerBookLiveData.observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                viewModel.viewPagerList.clear();
                List<Book> bookList = new ArrayList<>();
                for(int i=0;i<5;i++){
                    bookList.add(books.get(i));
                }
                viewModel.viewPagerList.addAll(bookList);
                initpoints();
            }
        });
        adapter = new MyAutoViewpagerAdapter(getContext(), viewModel.viewPagerList);
        binding.autoViewpager.setAdapter(adapter);
        binding.autoViewpager.setPageMargin(32);
        binding.autoViewpager.setClipChildren(false);

        binding.autoViewpager.setOffscreenPageLimit(3);
        binding.autoViewpager.setPageTransformer(true,new ScalePageTransformer());

        binding.autoViewpager.setCurrentItem(adapter.getRealSize() * 100 , false);

        binding.autoViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int realPosition = 0;
                if (adapter.getRealSize() != 0) {
                    realPosition = position % adapter.getRealSize();
                }
                selectedPoint(realPosition);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.hotRefreshMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompleteBookActivity.actionStartCompleteActivity(getContext(),"热门连载");
            }
        });
        binding.completeRefreshMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompleteBookActivity.actionStartCompleteActivity(getContext(),"完本精选");
            }
        });
        binding.recommandBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompleteBookActivity.actionStartCompleteActivity(getContext(),"推荐");
            }
        });
        binding.hotRefreshBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompleteBookActivity.actionStartCompleteActivity(getContext(),"热门连载");
            }
        });
        binding.completeBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompleteBookActivity.actionStartCompleteActivity(getContext(),"完本精选");
            }
        });


    }


    private void initHotRecyclerView(View view) {

        viewModel.refreshHotData();
        viewModel.hotRefreshLiveData.observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                final Book book = books.get(0);
                Glide.with(getContext()).load(MyApplicaton.url+book.getBookPic()).into(binding.hotRefreshPic);
                binding.hotRefreshBookName.setText(book.getBookName());
                binding.hotRefreshDesc.setText(book.getDesc());
                binding.hotRefreshAuthor.setText(book.getAuthor());
                hotBook = book;
                viewModel.hotRefreshList.clear();
                List<Book> list = new ArrayList<>();
                if(books.size()>=6){
                    for(int i=1;i<=6;i++){
                        list.add(books.get(i));
                    }

                }
                viewModel.hotRefreshList.clear();
                viewModel.hotRefreshList.addAll(list);
                hotRefreshAdapter.notifyDataSetChanged();
            }
        });

        binding.hotRefreshRecyclerview.setNestedScrollingEnabled(false);
        hotRefreshAdapter = new HotRefreshAdapter(getContext(),viewModel.hotRefreshList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        binding.hotRefreshRecyclerview.setLayoutManager(gridLayoutManager);
        binding.hotRefreshRecyclerview.setAdapter(hotRefreshAdapter);


    }
    private void initCompleteRecyclerView(View view) {
        viewModel.refreshCompleteBook();
        viewModel.completeBookLiveData.observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> bookList) {
                final Book book1 = bookList.get(0);
                completeBook = book1;
                Glide.with(getContext()).load(MyApplicaton.url+book1.getBookPic()).into(binding.completeBookPic);
                binding.completeBookName.setText(book1.getBookName());
                binding.completeDesc.setText(book1.getDesc());
                binding.completeBookAuthor.setText(book1.getAuthor());
                List<Book> list = new ArrayList<>();
                if(bookList.size()>=6){
                    for(int i=1;i<=6;i++){
                        list.add(bookList.get(i));
                    }

                }
                viewModel.completeBookList.clear();
                viewModel.completeBookList.addAll(list);
                hotRefreshAdapter.notifyDataSetChanged();
            }
        });
        binding.completebookRecyclerview.setNestedScrollingEnabled(false);
        completeAdapter = new CompleteBookAdapter(getContext(),viewModel.completeBookList);
        GridLayoutManager completeManager = new GridLayoutManager(getContext(),3);
        binding.completebookRecyclerview.setLayoutManager(completeManager);
       binding.completebookRecyclerview.setAdapter(completeAdapter);
    }



    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(runnable,2000);

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int position = binding.autoViewpager.getCurrentItem();
            if(!istouch){
                binding.autoViewpager.setCurrentItem(++position,true);
            }
            mHandler.postDelayed(this,2000);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(runnable);
    }


    private void selectedPoint(int realPosition) {

        for (int i = 0; i < binding.points.getChildCount(); i++) {
            View point = binding.points.getChildAt(i);
            if (i == realPosition) {
                point.setBackgroundResource(R.drawable.point_selected);
            } else {
                point.setBackgroundResource(R.drawable.point_unselected);
            }
        }

    }
    public void initpoints() {
        for (int i = 0; i < binding.points.getChildCount(); i++) {
            binding.points.removeAllViews();
        }
        for (int i = 0; i < viewModel.viewPagerList.size(); i++) {
            View point = new View(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(40, 40);
            params.leftMargin = 20;
            if(i!=0) {point.setBackgroundResource(R.drawable.point_unselected);}
            else {point.setBackgroundResource(R.drawable.point_selected);}
            point.setLayoutParams(params);
            binding.points.addView(point);
        }
    }



}
