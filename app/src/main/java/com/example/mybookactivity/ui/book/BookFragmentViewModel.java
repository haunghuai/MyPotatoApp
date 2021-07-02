package com.example.mybookactivity.ui.book;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.mybookactivity.logic.Repository;
import com.example.mybookactivity.logic.dao.entity.BookShelf;

import java.util.ArrayList;
import java.util.List;

public class BookFragmentViewModel extends ViewModel {
    //BookList书架的列表
    public List<BookShelf> bookShelfList;
    public BookFragmentViewModel(){
        bookShelfList = new ArrayList<>();
    }
    private MutableLiveData<Integer> refresh = new MutableLiveData<>();

    public LiveData<List<BookShelf>> bookShelfLivedata = Transformations.switchMap(refresh, new Function<Integer, LiveData<List<BookShelf>>>() {
        @Override
        public LiveData<List<BookShelf>> apply(Integer input) {
            return Repository.getInstance().selectBookShelf();
        }
    });

    public void refreshBookShelf(){
        refresh.setValue(1);
    }

}
