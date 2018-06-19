package com.comsoftstar.autobicycle.View.Main.Fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.comsoftstar.autobicycle.Base.BaseFragment;
import com.comsoftstar.autobicycle.Model.NetWork.http.Error;
import com.comsoftstar.autobicycle.Model.NetWork.http.HttpClient;
import com.comsoftstar.autobicycle.Model.NetWork.http.ResponseHandler;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.Util.Logs;
import com.comsoftstar.autobicycle.View.Main.Activity.DrivingCountActivity;
import com.comsoftstar.autobicycle.View.Main.Activity.DrivingHistoryActivity;
import com.comsoftstar.autobicycle.Model.Bean.Fragment1_Item;
import com.comsoftstar.autobicycle.View.Main.Activity.SettingActivity;
import com.comsoftstar.autobicycle.databinding.Fragment1Binding;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/9/25.
 */

public class Fragment1 extends BaseFragment<Fragment1Binding> implements View.OnClickListener{
    private static final String TAG = "bug";
    private Fragment1Binding mBinding;
    private Fragment1_Item config;

    @Override
    public int setLayoutId() {
        return R.layout.fragment1;
    }
    @Override
    public void initView(Fragment1Binding binding) {
        this.mBinding=binding;
        config=new Fragment1_Item("100","100","0m/min", "型号");
        mBinding.setFragment1item(config);
        mBinding.setOnclicklisten(this);
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
    public void onClick(View view){
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
                Logs.d(TAG, "onClick: drivitingconut" );
                Intent intent3 = new Intent(getActivity(), DrivingCountActivity.class);
                startActivity(intent3);
                break;
        }
    }
    private void forintent() {
        HttpClient client = new HttpClient(getContext());
        Call call = client.service("http://58.214.250.250:5051/").result("unifiedLogin", "admin", "123456");
        client.request(call, new ResponseHandler() {
            @Override
            public void onSuccess(Object o) {
              //  Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
               // List<Fragment1_Item> fragment1Item = (List<Fragment1_Item>) o;
               // Toast.makeText(getContext(), fragment1Item.get(0).getSystemName(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(int code, Error e) {
                // Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
}
