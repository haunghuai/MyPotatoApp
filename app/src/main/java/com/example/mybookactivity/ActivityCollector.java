package com.example.mybookactivity;

import android.app.Activity;

import com.example.mybookactivity.logic.network.BookContentServiceCreator;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {

    private ActivityCollector(){}
    public static ActivityCollector getInstance(){

        return ActivityCollectorHolder.sInstance;
    }
    private static class ActivityCollectorHolder{
        private static final ActivityCollector sInstance = new ActivityCollector();
    }

    private List<Activity> activities = new ArrayList<>();

    public void addActivity(Activity activity){
        activities.add(activity);
    }
    public void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public void fishAll(){
        for(Activity activity : activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }
}
