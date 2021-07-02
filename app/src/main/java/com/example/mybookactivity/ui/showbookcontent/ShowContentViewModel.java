package com.example.mybookactivity.ui.showbookcontent;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.mybookactivity.logic.Repository;
import com.example.mybookactivity.logic.model.BookPassage;

public class ShowContentViewModel extends ViewModel {
    public int id2=0;

    private MutableLiveData<Integer> data = new MutableLiveData<>();
    public LiveData<BookPassage> bookPassageLiveData = Transformations.switchMap(data, new Function<Integer, LiveData<BookPassage>>() {
        @Override
        public LiveData<BookPassage> apply(Integer input) {
            return Repository.getInstance().getBookPassage(id2,input);
        }
    });

    public void refreshContent(int id2,int id3){
        this.id2 = id2;
        data.setValue(id3);
    }


}
