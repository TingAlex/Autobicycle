package com.comsoftstar.autobicycle.Base;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.comsoftstar.autobicycle.App.App;

/**
 * Created by admin on 2017/12/12.
 */

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {
    private T binding;
    private Toolbar toolbar;
    protected View view;
    public Context baseContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseContext= App.context;
        binding = DataBindingUtil.inflate(inflater, setLayoutId(), container, false);
        initView(binding);
       // Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show();
        return binding.getRoot();
    }
    public abstract int setLayoutId();
    public abstract void initView(T binding);
    public void toast(Object o){
        Toast.makeText(getContext(),String.valueOf(o), Toast.LENGTH_SHORT).show();
    }
    public void addToolBar(final LinearLayout linearLayout, Toolbar toolbar){
        Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT,
                Toolbar.LayoutParams.WRAP_CONTENT);
        // 取控件aaa当前的布局参数
        params.height = 150;        // 当控件的高强制设成150象素
        params.width=Toolbar.LayoutParams.MATCH_PARENT;
        toolbar.setBackgroundColor(Color.BLACK);
        linearLayout.addView(toolbar,0,params);
    }
}
