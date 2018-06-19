package com.comsoftstar.autobicycle.View.Login.View;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.comsoftstar.autobicycle.Base.BaseFragment;
import com.comsoftstar.autobicycle.Util.Logs;
import com.comsoftstar.autobicycle.Util.RegexUtil;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.databinding.FragmentRegisterBinding;

import java.util.List;
import java.util.Timer;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2017/9/27.
 */
public class RegisterFragment2 extends BaseFragment<FragmentRegisterBinding> implements View.OnClickListener, EasyPermissions.PermissionCallbacks  {
    private static final String TAG = "bug";
    private EditText phone,number,name,name2,password,password2;
    private TextView nextstep;
    private CheckBox checkbox;
    private ImageView registerback;
    private FragmentRegisterBinding mbinding;

    @Override
    public int setLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    public void initView(FragmentRegisterBinding binding) {
        mbinding=binding;
        phone=binding.loginPhone;
        number=binding.loginNumber;
        name=binding.loginName;
        name2=binding.loginName2;
        password=binding.loginPassword0;
        password2=binding.loginPassword1;
        nextstep=binding.nextRegister;
//        nextstep.setOnClickListener(this);
        registerback=binding.backRegister;
//        registerback.setOnClickListener(this);
        checkbox=binding.enter;
        binding.setOnclicklisten(this);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    nextstep.setTextColor(Color.WHITE);
                    nextstep.setEnabled(true);
                }
            }
        });
//        //短信发送按钮
//        mbinding.btnSend.setTimes(3000);
//        mbinding.btnSend.setCountTimeMsgListener(new TimerTextView.CountTimeMsgListener() {
//            @Override
//            public void getCountDownTime(String time) {
//                mbinding.btnSend.setText(String.format("请在%s后重试", time));
//            }
//
//            @Override
//            public void finish() {
//                mbinding.btnSend.setClickable(true);
//                mbinding.btnSend.setText("发送");
//            }
//        });
        mbinding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("已发送");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        requestCodeQRCodePermissions();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.scan1:
                Logs.d(TAG, "onClick: 123");
                Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(getContext(), ScanActivity.class);
                startActivity(intent2);
                break;
            case R.id.next_register:
                Logs.d(TAG, "onClick: next");
             //   tonextstep();
                break;
            case R.id.back_register:
                Logs.d(TAG, "onClick: back");
                getFragmentManager().popBackStack();
                break;


        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(getContext(), perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }
    //下一步
    public void tonextstep(){

        if (!RegexUtil.checkMobile(phone.getText().toString())){
            Toast.makeText(getContext(), "手机号有误", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(number.getText())){
            Toast.makeText(getContext(), "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!RegexUtil.checkname(name.getText().toString())){
            Toast.makeText(getContext(), "姓名不符合要求！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!(RegexUtil.checkInputchart(name2.getText().toString()) && name2.length()>2 && name2.length()<14)){
            Toast.makeText(getContext(), "昵称不符合要求！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.getText().toString().equals(password2.getText().toString())){
            Toast.makeText(getContext(), "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!RegexUtil.checkpassword6_20(password.getText().toString())){
            Toast.makeText(getContext(), "密码不符合要求！！", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getContext(), "下一步", Toast.LENGTH_SHORT).show();

    }
}
