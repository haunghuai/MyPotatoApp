package com.example.mybookactivity.ui.recommand;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;


import com.example.mybookactivity.logic.Repository;
import com.example.mybookactivity.logic.dao.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class RecommandFragmentViewModel extends ViewModel {
    //viewpager的数据集list
    public List<Book> viewPagerList;

    //Hotrefresh布局的数据集
    public List<Book> hotRefreshList;

    //CompleteBook布局的数据
    public List<Book> completeBookList;


    public RecommandFragmentViewModel(){
        viewPagerList = new ArrayList<>();
        hotRefreshList = new ArrayList<>();
        completeBookList = new ArrayList<>();
    }


    private MutableLiveData<Integer> bookid = new MutableLiveData<>();


    public LiveData<Book> bookMutableLiveData = Transformations.switchMap(bookid, new Function<Integer, LiveData<Book>>() {
        @Override
        public LiveData<Book> apply(Integer input) {
            return Repository.getInstance().getBookInfo(input);
        }
    });
    public void getBookInfo(int id){
        bookid.setValue(id);
    }



    //viewpager的监听
    private MutableLiveData<Integer> autoVPRefresh = new MutableLiveData<>();
    public LiveData<List<Book>> viewpagerBookLiveData = Transformations.switchMap(autoVPRefresh, new Function<Integer, LiveData<List<Book>>>() {
        @Override
        public LiveData<List<Book>> apply(Integer input) {
            return Repository.getInstance().getRecommandBook();
        }
    });
    public void refreshViewPager(){
        autoVPRefresh.setValue(1);
    }

    //hotRefresh的监听
    private MutableLiveData<Integer> hotRefresh = new MutableLiveData<>();
    public LiveData<List<Book>> hotRefreshLiveData = Transformations.switchMap(hotRefresh, new Function<Integer, LiveData<List<Book>>>() {
        @Override
        public LiveData<List<Book>> apply(Integer input) {
            return Repository.getInstance().getHotRefreshBook();
        }
    });
    public void refreshHotData(){
        hotRefresh.setValue(1);
    }

    //completeBook的监听
    private MutableLiveData<Integer> complete = new MutableLiveData<>();
    public void refreshCompleteBook(){
        complete.setValue(1);
    }
    public LiveData<List<Book>> completeBookLiveData = Transformations.switchMap(complete, new Function<Integer, LiveData<List<Book>>>() {
        @Override
        public LiveData<List<Book>> apply(Integer input) {
            return Repository.getInstance().getCompleteBook();
        }
    });

}
