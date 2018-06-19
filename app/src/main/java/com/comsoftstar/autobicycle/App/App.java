package com.comsoftstar.autobicycle.App;

import android.app.Application;
import android.content.Context;

import com.comsoftstar.autobicycle.Util.Logs;


/**
 * Created by Administrator on 2017/9/28.
 */

public class App extends Application {


    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        Logs.init(true);
        context=getApplicationContext();
    }
}
