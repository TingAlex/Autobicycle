package com.comsoftstar.autobicycle.Login.View;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.comsoftstar.autobicycle.Public.Util.RegexUtil;
import com.comsoftstar.autobicycle.R;

/**
 * Created by Administrator on 2017/9/27.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {
    private EditText phone,number,name,name2,password,password2;
    private TextView nextstep;
    private CheckBox checkbox;
    private ImageView registerback;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_register,container,false);
        phone=(EditText)view.findViewById(R.id.login_phone);
        number=(EditText)view.findViewById(R.id.login_number);
        name=(EditText)view.findViewById(R.id.login_name);
        name2=(EditText)view.findViewById(R.id.login_name2);
        password=(EditText)view.findViewById(R.id.login_password0);
        password2=(EditText)view.findViewById(R.id.login_password1);
        nextstep=(TextView)view.findViewById(R.id.next_register);
        nextstep.setOnClickListener(this);
        registerback=(ImageView)view.findViewById(R.id.back_register);
        registerback.setOnClickListener(this);
        checkbox=(CheckBox)view.findViewById(R.id.enter);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    nextstep.setTextColor(Color.WHITE);
                    nextstep.setEnabled(true);
                }
            }
        });
        return  view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next_register:
                tonextstep();
                break;
            case R.id.sendmessage:
                sendmessage();
                break;
            case R.id.back_register:
                getFragmentManager().popBackStack();
                break;
        }
    }

    //发送验证码
    public void sendmessage(){

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
