package com.comsoftstar.autobicycle.View.Main.Mine;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.comsoftstar.autobicycle.App.ActivityManage;
import com.comsoftstar.autobicycle.Base.BaseActivity;
import com.comsoftstar.autobicycle.Control.MyToolBar;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.View.Login.View.LoginActivity;
import com.comsoftstar.autobicycle.databinding.ActivitySettingBinding;

public class SettingActivity extends BaseActivity<ActivitySettingBinding> implements View.OnClickListener{
private Button exitlogin;

    @Override
    public int setLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView(ActivitySettingBinding binding) {
        Toolbar toolbar=MyToolBar.newInstance()
                .init(new Toolbar(this))
                .addTitle(getString(R.string.Setting))
                .addLogo()
                .addHome(R.drawable.ic_chevron_left_black_24dp, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        addToolBar(binding.toolbarSetting,toolbar);
        exitlogin=(Button)findViewById(R.id.exitlogin);
        exitlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.exitlogin:
                ActivityManage.finishall();
                Intent intent=new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
