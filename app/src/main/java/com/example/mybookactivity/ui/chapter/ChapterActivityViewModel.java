package com.example.mybookactivity.ui.chapter;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.mybookactivity.logic.Repository;
import com.example.mybookactivity.logic.model.BookIndex;

import java.util.ArrayList;
import java.util.List;

public class ChapterActivityViewModel extends ViewModel {
    public List<BookIndex.DataBean.ListBeanX.ListBean> listBeanList;
        public ChapterActivityViewModel(){
            listBeanList = new ArrayList<>();
        }



        private MutableLiveData<Integer> liveData = new MutableLiveData<>();

        public LiveData<BookIndex> bookIndexLiveData = Transformations.switchMap(liveData, new Function<Integer, LiveData<BookIndex>>() {
            @Override
            public LiveData<BookIndex> apply(Integer input) {
                return Repository.getInstance().getBookIndex(input);
            }
        });
        public void refreshChapter(int id){
            liveData.setValue(id);
        }

}
