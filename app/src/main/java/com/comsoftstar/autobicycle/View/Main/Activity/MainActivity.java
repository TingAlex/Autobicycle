package com.comsoftstar.autobicycle.View.Main.Activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.comsoftstar.autobicycle.Base.BaseActivity;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.View.Main.Fragment.Fragment1;
import com.comsoftstar.autobicycle.View.Main.Fragment.Fragment2;
import com.comsoftstar.autobicycle.Control.MyViewPager;
import com.comsoftstar.autobicycle.View.Main.Fragment.Fragment3;
import com.comsoftstar.autobicycle.databinding.ActivityMainBinding;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity<ActivityMainBinding> {
    MyViewPager mviewPager;
    MainAdapter pagerAdapter;
    AlphaTabsIndicator alphaTabsIndicator;

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(ActivityMainBinding binding) {
        mviewPager=(MyViewPager)findViewById(R.id.viewpager);
        mviewPager.setNoScroll(true);
        alphaTabsIndicator=(AlphaTabsIndicator)findViewById(R.id.alphaIndicator);

        pagerAdapter = new MainAdapter(getSupportFragmentManager());
        mviewPager.setAdapter(pagerAdapter);
        mviewPager.addOnPageChangeListener(pagerAdapter);
        alphaTabsIndicator.setViewPager(mviewPager);
    }

    private class MainAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

        private List<Fragment> fragments = new ArrayList<>();
      //  private String[] titles = {"微信", "通讯录", "发现"};

        public MainAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(new Fragment1());
            fragments.add(new Fragment2());
            fragments.add(new Fragment3());

        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
