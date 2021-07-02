package com.example.mybookactivity.ui.chapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mybookactivity.ActivityCollector;
import com.example.mybookactivity.databinding.ActivityChapterBinding;
import com.example.mybookactivity.logic.model.BookIndex;

import java.util.ArrayList;
import java.util.List;

public class ChapterActivity extends AppCompatActivity {

    public  ChapterActivityViewModel viewModel;
    public ActivityChapterBinding binding;
    public ChapterRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.getInstance().addActivity(this);
        binding = ActivityChapterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = ViewModelProviders.of(this).get(ChapterActivityViewModel.class);

        int  id = getIntent().getIntExtra("id",248872);
        String bookAuthor = getIntent().getStringExtra("bookAuthor");
        String bookUrl = getIntent().getStringExtra("bookUrl");
        Log.e("meme", "onCreate: "+id);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        viewModel.refreshChapter(id);
        adapter = new ChapterRecyclerViewAdapter(this,viewModel.listBeanList,id,bookUrl,bookAuthor);
        viewModel.bookIndexLiveData.observe(this, new Observer<BookIndex>() {
            @Override
            public void onChanged(BookIndex bookIndex) {
            binding.chapterTitle.setText(bookIndex.getData().getName());
                List<BookIndex.DataBean.ListBeanX.ListBean> list = new ArrayList<>();
                int size = bookIndex.getData().getList().size();
                for(int i=0;i<size;i++){
                    BookIndex.DataBean.ListBeanX listBeanX =bookIndex.getData().getList().get(i);
                    int size1 = listBeanX.getList().size();
                    for(int j=0;j<size1;j++){
                        BookIndex.DataBean.ListBeanX.ListBean listBean = listBeanX.getList().get(j);
                        list.add(listBean);
                    }

                }
                viewModel.listBeanList.clear();
                viewModel.listBeanList.addAll(list);
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }
        });
        binding.chapterRecyclerView.setAdapter(adapter);
        binding.chapterRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.chapterRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        binding.chapterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public static void actionStartChapterActivity(Context context,int id,String url,String bookAuthor){
        Intent intent = new Intent(context,ChapterActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("bookUrl",url);
        intent.putExtra("bookAuthor",bookAuthor);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
    }
}