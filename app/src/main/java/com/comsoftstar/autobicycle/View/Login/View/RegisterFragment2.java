package com.comsoftstar.autobicycle.View.Login.View;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.comsoftstar.autobicycle.Base.BaseFragment;
import com.comsoftstar.autobicycle.Control.CountDownButton;
import com.comsoftstar.autobicycle.Interface.CallBack;
import com.comsoftstar.autobicycle.Interface.Value;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Register.R_Result;
import com.comsoftstar.autobicycle.Model.NetWork.NetGet.NetUtil;
import com.comsoftstar.autobicycle.Util.Logs;
import com.comsoftstar.autobicycle.Util.RegexUtil;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.databinding.FragmentRegisterBinding;

import java.util.List;
import java.util.Map;
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
        registerback=binding.backRegister;
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

        //验证码发送
        mbinding.btnSend.setinterface(new CountDownButton.ICountDownButton() {
            @Override
            public void message(String msg) {
                toast(msg);
            }
        });
        mbinding.btnSend.setOnClickListener(mbinding.loginPhone,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetUtil.getInstance().VeriCode(mbinding.loginPhone.getText().toString(), Value.VerCode.REGISTER.getValue(), new CallBack<R_Result>() {
                    @Override
                    public void success(R_Result result) {
                        Toast.makeText(getContext(), result.getResult(), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void faile(String s) {

                    }
                });

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

                Map<String,String> paramter=new ArrayMap<>();
                paramter.put("opType","Register");
                paramter.put("loginName","18068261236");
                paramter.put("Password","123456");
                //验证码
                paramter.put("Vericode",number.getText().toString());
                //采集模块ID
                paramter.put("ModuleID","31ffdc05335436331560045700000000");
                //电池组ID
                paramter.put("PackID","1");
                //营业点编号
                paramter.put("Pointno","1");
                //电池数
                paramter.put("CellNum","1");
                //额定电压
                paramter.put("StandardVol","1");
                //额定电流
                paramter.put("StandardCur","1");
                //电容
                paramter.put("Capacity","1");

                NetUtil.getInstance().Register(paramter, new CallBack<R_Result>() {
                    @Override
                    public void success(R_Result result) {

                    }

                    @Override
                    public void faile(String s) {

                    }
                });
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
