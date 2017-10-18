package com.comsoftstar.autobicycle.Main.MY.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.comsoftstar.autobicycle.Login.View.LoginActivity;
import com.comsoftstar.autobicycle.Public.ActivityManage;
import com.comsoftstar.autobicycle.Public.BaseActivity;
import com.comsoftstar.autobicycle.R;

public class SettingActivity extends BaseActivity implements View.OnClickListener{
private Button exitlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        final Toolbar toolbar=(Toolbar)findViewById(R.id.settingtoolbar);
        toolbar.setTitle("设置");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);
            actionBar.setHomeButtonEnabled(true);

        }
        exitlogin=(Button)findViewById(R.id.exitlogin);
        exitlogin.setOnClickListener(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
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
