package com.comsoftstar.autobicycle.View.Main.Activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.comsoftstar.autobicycle.Base.BaseActivity;
import com.comsoftstar.autobicycle.Control.MyToolBar;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.databinding.ActivityAboutBinding;

public class AboutActivity extends BaseActivity<ActivityAboutBinding> {

    @Override
    public int setLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initView(ActivityAboutBinding binding) {
       Toolbar toolbar= MyToolBar.newInstance()
               .init(new Toolbar(this))
               .addTitle(getString(R.string.About))
               .addLogo()
               .addHome(R.drawable.ic_chevron_left_black_24dp, new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       finish();
                   }
               });
       addToolBar(binding.toolbarAbout,toolbar);
    }

}
