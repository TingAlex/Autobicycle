package com.comsoftstar.autobicycle.Control;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.comsoftstar.autobicycle.Base.BaseActivity;
import com.comsoftstar.autobicycle.R;

/**
 * Created by admin on 2017/12/18.
 */

public class MyToolBar {
    private Toolbar toolbar;

    private MyToolBar() {
    }

    private static volatile MyToolBar myToolBar = null;

    public static MyToolBar newInstance() {
        if (myToolBar == null) {
            synchronized (MyToolBar.class) {
                if (myToolBar == null) {
                    myToolBar = new MyToolBar();
                }
            }
        }
        return myToolBar;
    }

    //第一步
    public MyToolBar init(@NonNull Toolbar toolbar) {
        this.toolbar = toolbar;
        this.toolbar.setTitleTextColor(Color.WHITE);
        this.toolbar.setBackgroundResource(R.color.blackgray);
        return this;
    }

    public MyToolBar addTitle(@NonNull String title) {
        this.toolbar.setTitle(title);
        return this;
    }

    public MyToolBar setOverflowIcon(Context context) {
        this.toolbar.setOverflowIcon(context.getApplicationContext().getResources().getDrawable(R.drawable.ic_more_vert_black_24dp));
        return this;
    }

    public MyToolBar addLogo() {
        this.toolbar.setLogo(R.drawable.ic_toolbar_logo);
        this.toolbar.setContentInsetStartWithNavigation(0);
        return this;
    }

    public MyToolBar setColor(int bgcolor, @ColorInt @ColorRes int titlecolor) {
        toolbar.setTitleTextColor(titlecolor);
        toolbar.setBackgroundResource(bgcolor);
        return this;
    }

    public MyToolBar addMenu(int menu, Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
        toolbar.inflateMenu(menu);
        //设置选项菜单的菜单项的点击事件
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        return this;
    }

    //最后一步
    public Toolbar addHome(int home, View.OnClickListener onClickListener) {
        switch (home) {
            case -1:
                break;
            case 0:
                toolbar.setNavigationIcon(R.drawable.ic_format_list_bulleted_black_24dp);
                break;
            case 1:
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
                break;
            default:
                toolbar.setNavigationIcon(home);
                break;
        }
        toolbar.setNavigationOnClickListener(onClickListener);
        return toolbar;
    }
}
