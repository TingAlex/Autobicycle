package com.comsoftstar.autobicycle.Base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.comsoftstar.autobicycle.Public.ActivityManage;
import com.comsoftstar.autobicycle.R;

import org.xutils.x;
import org.zackratos.ultimatebar.UltimateBar;


/**
 * Created by admin on 2017/12/12.
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {
    private T binding;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,setLayoutId());
        initView(binding);
        x.view().inject(this);
        ActivityManage.addactivity(this);
        UltimateBar ultimateBar=new UltimateBar(this);
        ultimateBar.setColorBar(getResources().getColor(R.color.blackgray),1);
    }
   public abstract int setLayoutId();
    public abstract void initView(T binding);
    public void toast(Object o){
        Toast.makeText(this,String.valueOf(o), Toast.LENGTH_SHORT).show();
    }
    public void toast(Object o,int n){
        Toast.makeText(this,String.valueOf(o), Toast.LENGTH_LONG).show();
    }
    public void addToolBar(final LinearLayout linearLayout, Toolbar toolbar){
        Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT,
                Toolbar.LayoutParams.WRAP_CONTENT);
        // 取控件aaa当前的布局参数
        params.height = dip2px(45.0f);        // 当控件的高强制设成150象素
        params.width=Toolbar.LayoutParams.MATCH_PARENT;
        linearLayout.addView(toolbar,0,params);
    }
    public int dip2px(float dip){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,getResources().getDisplayMetrics());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManage.finishthis(this);
    }
}
