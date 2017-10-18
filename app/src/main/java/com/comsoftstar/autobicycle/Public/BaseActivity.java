package com.comsoftstar.autobicycle.Public;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.comsoftstar.autobicycle.R;

import org.xutils.x;
import org.zackratos.ultimatebar.UltimateBar;

/**
 * Created by Administrator on 2017/9/28.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        ActivityManage.addactivity(this);
        UltimateBar ultimateBar=new UltimateBar(this);
        ultimateBar.setColorBar(getResources().getColor(R.color.blackgray),1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManage.finishthis(this);
    }
}
