package com.comsoftstar.autobicycle.App;

import android.app.Application;
import android.content.Context;



/**
 * Created by Administrator on 2017/9/28.
 */

public class App extends Application {


    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }
}
