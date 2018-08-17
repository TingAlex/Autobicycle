package com.comsoftstar.autobicycle.View.Main.CarSituation;

import android.content.Intent;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Toast;

import com.comsoftstar.autobicycle.Base.BaseFragment;
import com.comsoftstar.autobicycle.Business.StaticData;
import com.comsoftstar.autobicycle.Model.Bean.HomePage;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.Util.Logs;
import com.comsoftstar.autobicycle.Model.Bean.Fragment1_Item;
import com.comsoftstar.autobicycle.View.Interface.Iview.IFragment1View;
import com.comsoftstar.autobicycle.View.Main.Mine.SettingActivity;
import com.comsoftstar.autobicycle.View.Presenter.PagePresenter;
import com.comsoftstar.autobicycle.databinding.Fragment1Binding;

import java.util.Map;

/**
 * Created by Administrator on 2017/9/25.
 */

public class Fragment1 extends BaseFragment<Fragment1Binding> implements View.OnClickListener, IFragment1View {
    private static final String TAG = "bug";
    private Fragment1Binding mBinding;
    private Fragment1_Item config;
    private PagePresenter<IFragment1View> pagePresenter;
    private HomePage homePage;

    @Override
    public int setLayoutId() {
        return R.layout.fragment1;
    }

    @Override
    public void initView(Fragment1Binding binding) {
        this.mBinding = binding;
        config = new Fragment1_Item("0", "0", "0m/min", "型号");
        mBinding.setFragment1item(config);
        mBinding.setOnclicklisten(this);
        pagePresenter = new PagePresenter<IFragment1View>(getActivity().getApplicationContext(), this);
        Map<String, String> params = new ArrayMap<>();
        params.put("loginName", StaticData.loginResults.get(0).getLoginName());
        params.put("cfgID", StaticData.loginResults.get(0).getCfgID());
        params.put("IMEI", StaticData.loginResults.get(0).getIMEI());
        pagePresenter.getHomePage(params);

//        mBinding.fragment1context.setOnclicklisten(this);
//        mainSetting = view.findViewById(R.id.main_setting);
//        this.mainSetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_setting:
                Logs.d(TAG, "onClick: 123");
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.drivitinghistory:
                Logs.d(TAG, "onClick: drivitinghistory");
                Intent intent2 = new Intent(getActivity(), DrivingHistoryActivity.class);
                startActivity(intent2);
                break;
            case R.id.drivitingconut:
                Logs.d(TAG, "onClick: drivitingconut");
                Intent intent3 = new Intent(getActivity(), DrivingCountActivity.class);
                intent3.putExtra("lc", homePage.getWorkMiles());
                intent3.putExtra("pb", homePage.getDayCom());
                startActivity(intent3);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // getContext().stopService(new Intent(getActivity(), NetService.class));
        super.onDestroy();
    }

    @Override
    public void faileMsg(String msg) {
        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
        String lc = homePage.getSYLC();

        int n = lc.indexOf(".");
        homePage.setSYLC(lc.substring(0, n + 3));
        config = new Fragment1_Item(homePage.getSYLC(), homePage.getSYDL().replace("%", ""), "0m/min", "型号");
        mBinding.setFragment1item(config);
    }
}
