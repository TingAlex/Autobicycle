package com.comsoftstar.autobicycle;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

/**
 * Created by Administrator on 2017/9/28.
 */

public class FirstLoad extends Application {
    public Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        // 设置是否输出debug
        x.Ext.setDebug(false);
    }

}
