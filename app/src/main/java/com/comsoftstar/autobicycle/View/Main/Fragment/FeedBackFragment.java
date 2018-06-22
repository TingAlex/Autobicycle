package com.comsoftstar.autobicycle.View.Main.Fragment;




import android.support.v7.widget.Toolbar;
import android.view.View;

import com.comsoftstar.autobicycle.Base.BaseFragment;
import com.comsoftstar.autobicycle.Control.MyToolBar;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.databinding.FragmentFeedbackBinding;

/**
 * Created by SJ on 2018/6/22.
 */

public class FeedBackFragment extends BaseFragment<FragmentFeedbackBinding> {
    private FragmentFeedbackBinding binding;
    @Override
    public int setLayoutId() {
        return R.layout.fragment_feedback;
    }

    @Override
    public void initView(FragmentFeedbackBinding binding) {
        this.binding=binding;
        Toolbar myToolBar=MyToolBar.newInstance().init(new Toolbar(getContext()))
                .addLogo()
                .addTitle("用户反馈")
                .addHome(1, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        addToolBar(binding.layoutFeedback,myToolBar);
    }
}
