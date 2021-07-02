package com.example.mybookactivity.ui.search;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.mybookactivity.logic.Repository;
import com.example.mybookactivity.logic.model.BookSearch;

import java.util.ArrayList;
import java.util.List;

public class SearchActivityViewModel extends ViewModel {

    public List<BookSearch.DataBean> dataBeanList;
    public SearchActivityViewModel(){
        dataBeanList = new ArrayList<>();
    }
    private MutableLiveData<String> bookNameLiveData=new MutableLiveData<>();

    public LiveData<BookSearch> bookSearchLiveData = Transformations.switchMap(bookNameLiveData, new Function<String, LiveData<BookSearch>>() {
        @Override
        public LiveData<BookSearch> apply(String input) {
            return Repository.getInstance().getBookSearch(input);
        }
    });


    public void searchBook(String bookName){
        bookNameLiveData.setValue(bookName);
    }
}
