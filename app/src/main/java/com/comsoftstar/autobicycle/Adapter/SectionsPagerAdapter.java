package com.comsoftstar.autobicycle.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.comsoftstar.autobicycle.View.Main.CarSituation.Fragment1;
import com.comsoftstar.autobicycle.View.Main.Service.Fragment2;

/**
 * Created by Administrator on 2017/9/25.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private Context context2;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        context2 = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fragment1();//子类页面
            case 1:
                return new Fragment2();
        }
        return new Fragment2();
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "12";
            case 1:
                return "12";
            case 2:
                return "12";
        }
        return null;
    }
}

