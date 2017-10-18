package com.comsoftstar.autobicycle.Main.CK.View;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.comsoftstar.autobicycle.Main.CK.ViewModel.Fragment1_Item;
import com.comsoftstar.autobicycle.Main.MY.View.SettingActivity;
import com.comsoftstar.autobicycle.Public.NetWork.http.Error;
import com.comsoftstar.autobicycle.Public.NetWork.http.HttpClient;
import com.comsoftstar.autobicycle.Public.NetWork.http.ResponseHandler;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.databinding.Fragment1Binding;

import java.util.Arrays;

import cn.com.heaton.blelib.BleLisenter;
import cn.com.heaton.blelib.BleManager;
import cn.com.heaton.blelib.BleVO.BleDevice;
import retrofit2.Call;

/**
 * Created by Administrator on 2017/9/25.
 */

public class Fragment1 extends Fragment implements View.OnClickListener{
    private BleManager mManager;
    private Fragment1Binding mBinding;
  //  private MyReceiver myReceiver;
    private Fragment1_Item config;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // View view=inflater.inflate (R.layout.fragment1, container, false);
         mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment1, container, false);
        config=new Fragment1_Item("100","100","0m/min", "型号");
        mBinding.setFragment1item(config);
        mBinding.setOnclicklisten(this);
        return mBinding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //启动服务
//        getContext().startService(new Intent(getActivity(), NetService.class));
//        //注册广播接收器
//        myReceiver=new MyReceiver();
//        IntentFilter filter=new IntentFilter();
//        filter.addAction("com.ljq.activity.CountService");
//        getContext().registerReceiver(myReceiver,filter);
    }


    /**
     * 获取广播数据
     *
     * @author jiqinlin
     *
     *
     */
//    public class MyReceiver extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Bundle bundle=intent.getExtras();
//            int count=bundle.getInt("count");
//           // Fragment1_Item config=new Fragment1_Item(String.valueOf(count),"100","0m/min", "型号");
//            config.setLength(String.valueOf(count));
////            editText.setText(count+"");
//        }
//    }
@Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.openlock:
                //initBle();\
               // share();
                Intent intent3=new Intent(Intent.ACTION_SEND);
                intent3.setType("*/*");
                intent3.putExtra(Intent.EXTRA_SUBJECT, "Share");
                intent3.putExtra(Intent.EXTRA_TEXT, "I have successfully share my message through my app");
                intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent3, "分享"));
             //   Toast.makeText(getContext(), "open", Toast.LENGTH_SHORT).show();
                break;
            case R.id.closelock:
                Toast.makeText(getContext(), "close", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_setting:
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.drivitinghistory:
                Intent intent2 = new Intent(getActivity(), drivinghistoryActivity.class);
                startActivity(intent2);
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

    private void initBle() {
        try {
            mManager = BleManager.getInstance(getContext(), mLisenter);
            //mManager.registerBleListener(mLisenter);
            boolean result = false;
            if (mManager != null) {
                result = mManager.startService();
                if (!mManager.isBleEnable()) {//蓝牙未打开
                    mManager.turnOnBlueTooth(getActivity());
                } else {//已打开
                    //开始扫描
                    mManager.scanLeDevice(true);
                }
            }

            if (!result) {
                Toast.makeText(getContext(), "服务绑定失败", Toast.LENGTH_SHORT).show();

                if (mManager != null) {
                    mManager.startService();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BleLisenter mLisenter = new BleLisenter() {

        @Override
        public void onStart() {
            // ...
            //可以选择性实现该方法   不需要则不用实现(以下类同)
        }

        @Override
        public void onStop() {
            // ...
        }

        @Override
        public void onConnectTimeOut() {
            //...
        }

        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            //扫描到设备一次
        }

        @Override
        public void onConnectionChanged(final BleDevice device) {
            // 蓝牙连接状态发生改变
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt) {
            // 已经搜索到蓝牙服务
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt) {
            super.onDescriptorRead(gatt);
        }

        @Override
        public void onChanged(BluetoothGattCharacteristic characteristic) {
            Toast.makeText(getContext(), "data===" + Arrays.toString(characteristic.getValue()), Toast.LENGTH_SHORT).show();

            //可以选择性实现该方法   不需要则不用实现
            //硬件mcu 返回数据
        }

        @Override
        public void onWrite(BluetoothGatt gatt) {
            //  ...
        }

        @Override
        public void onRead(BluetoothDevice device) {
            // ...
        }

        @Override
        public void onDescriptorWriter(BluetoothGatt gatt) {
            // ...
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // User chose not to enable Bluetooth.
        if (requestCode == BleManager.REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(getContext(), "蓝牙开启失败", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (mManager != null) {
                mManager.scanLeDevice(true);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getContext().stopService(new Intent(getActivity(), NetService.class));
        super.onDestroy();

    }
}
