package com.comsoftstar.autobicycle.View.Main.Mine;




import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.comsoftstar.autobicycle.Adapter.FeedBackAdapter;
import com.comsoftstar.autobicycle.Base.BaseActivity;
import com.comsoftstar.autobicycle.Control.MyToolBar;
import com.comsoftstar.autobicycle.App.Value;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Main.FeedBackBean;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.R_Result;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.View.Presenter.FeedBackPresenter;
import com.comsoftstar.autobicycle.databinding.ActivityFeedbackBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SJ on 2018/6/22.
 */

public class FeedBackActivity extends BaseActivity<ActivityFeedbackBinding> implements View.OnClickListener,FeedBackPresenter.IFeedBackView{
    private ActivityFeedbackBinding binding;
    private  FeedBackAdapter adapter;
    private FeedBackPresenter feedBackPresenter;
    @Override
    public int setLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initView(ActivityFeedbackBinding binding) {
        this.binding=binding;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        binding.recyFeedback.setLayoutManager(layoutManager);
        binding.setOnclicklisten(this);
        Toolbar myToolBar=MyToolBar.newInstance().init(new Toolbar(this))
                .addLogo()
                .addTitle("用户反馈")
                .addHome(1, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        addToolBar(binding.layoutFeedback,myToolBar);


        initdata();
    }

    /**
     * 初始化数据
     */
    public void initdata(){
        feedBackPresenter=new FeedBackPresenter(this);
        adapter=new FeedBackAdapter(getApplicationContext(), new ArrayList<FeedBackBean>());
        binding.recyFeedback.setAdapter(adapter);
        feedBackPresenter.getFeedBack(Value.UserName);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //提交
            case R.id.btn_submit:
                String feed=binding.editFeed.getText().toString();
                if (feed==null||TextUtils.isEmpty(feed)){
                    Toast.makeText(this, "请填写反馈内容！", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String,String> params=new HashMap<>();
                params.put("loginName", Value.UserName);
                params.put("Guid","");
                params.put("Suggestion",feed);
                feedBackPresenter.saveFeed(params);
                break;
        }
    }


    //region FeedBackPresenter.IFeedBackView接口实现
    @Override
    public void feedback(List<FeedBackBean> feedBackBeans) {
        adapter.setdata(feedBackBeans);
    }

    @Override
    public void savefeed(R_Result result) {
        Toast.makeText(this, result.getResult(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void faile(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
    //endregion
}
