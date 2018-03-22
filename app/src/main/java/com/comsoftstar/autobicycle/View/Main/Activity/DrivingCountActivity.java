package com.comsoftstar.autobicycle.View.Main.Activity;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.comsoftstar.autobicycle.Base.BaseActivity;
import com.comsoftstar.autobicycle.Control.MyToolBar;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.databinding.ActivityDrivingCountBinding;

public class DrivingCountActivity extends BaseActivity<ActivityDrivingCountBinding> {
    ActivityDrivingCountBinding mbinding;
    @Override
    public int setLayoutId() {
        return R.layout.activity_driving_count;
    }

    @Override
    public void initView(ActivityDrivingCountBinding binding) {
        this.mbinding=binding;
        MyToolBar myToolBar=MyToolBar.newInstance();
        Toolbar toolbar=myToolBar.init(new Toolbar(this))
                .addTitle(getString(R.string.DrivingCount))
                .addHome(1, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        addToolBar(binding.drivercountlinearlayout,toolbar);
    }
}
