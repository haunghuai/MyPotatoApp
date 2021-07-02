package com.example.mybookactivity.ui.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.mybookactivity.ActivityCollector;
import com.example.mybookactivity.databinding.ActivitySearchBinding;
import com.example.mybookactivity.logic.model.BookSearch;
import com.example.mybookactivity.ui.recommand.RecommandFragmentViewModel;

public class SearchActivity extends AppCompatActivity {

    public ActivitySearchBinding binding;
    public SearchActivityViewModel viewModel;

    //recyclerview
    public SearchRecyclerViewAdapter adapter;
    public LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.getInstance().addActivity(this);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        viewModel = ViewModelProviders.of(this).get(SearchActivityViewModel.class);
        setContentView(binding.getRoot());

        init();

        binding.searchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    viewModel.searchBook(s.toString());
            }
        });
        viewModel.bookSearchLiveData.observe(this, new Observer<BookSearch>() {
            @Override
            public void onChanged(BookSearch bookSearch) {
                if(bookSearch!=null){
                    viewModel.dataBeanList.clear();
                    viewModel.dataBeanList.addAll(bookSearch.getData());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(SearchActivity.this,"没有查询到任何数据",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        adapter = new SearchRecyclerViewAdapter(this,viewModel.dataBeanList);
        manager = new LinearLayoutManager(this);
        binding.searchBookRv.setAdapter(adapter);
        binding.searchBookRv.setLayoutManager(manager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
    }
}