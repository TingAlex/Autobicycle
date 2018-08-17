package com.comsoftstar.autobicycle.App;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/27.
 */

public class ActivityManage {
    public static List<AppCompatActivity> activities = new ArrayList<AppCompatActivity>();

    public static void addactivity(AppCompatActivity activity) {
        activities.add(activity);
    }

    public static void finishthis(AppCompatActivity activity) {
        activities.remove(activity);
    }

    public static void finishall() {
        for (AppCompatActivity activity : activities) {
            if (!activity.isFinishing())
                activity.finish();
        }
    }
}
