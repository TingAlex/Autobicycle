package com.comsoftstar.autobicycle.View.Login.View;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.comsoftstar.autobicycle.Base.BaseActivity;
import com.comsoftstar.autobicycle.Control.SjieInputEditText;
import com.comsoftstar.autobicycle.Util.RegexUtil;
import com.comsoftstar.autobicycle.Util.Tools;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.View.Login.Presenter.LoginPresenter;
import com.comsoftstar.autobicycle.View.Main.Activity.MainActivity;
import com.comsoftstar.autobicycle.databinding.ActivityLogin2Binding;

import com.wang.avi.AVLoadingIndicatorView;
public class LoginActivity extends BaseActivity<ActivityLogin2Binding> implements View.OnClickListener,Login_inteface{
public SjieInputEditText textInputEditTextaccount,textInputEditTextpassword;
    private TextView register;
    private Button login;
    private LoginPresenter loginPresenter;
    private CheckBox ischeckbox;            //记住密码
    private boolean ischeck;                //是否记住密码
    private AVLoadingIndicatorView loadingIndicatorView;  //加载动画

    @Override
    public int setLayoutId() {
        return R.layout.activity_login2;
    }

    @Override
    public void initView(ActivityLogin2Binding binding) {
        loginPresenter=new LoginPresenter(this);
        // 初始化
        initUI();
    }
    private void initUI(){
        textInputEditTextaccount=(SjieInputEditText)findViewById(R.id.login_account);
        textInputEditTextpassword=(SjieInputEditText)findViewById(R.id.login_password);
        loadingIndicatorView=(AVLoadingIndicatorView)findViewById(R.id.login_load);
        register=(TextView)findViewById(R.id.register);
        register.setOnClickListener(this);
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(this);
        ischeckbox=(CheckBox)findViewById(R.id.login_ischeck);
        ischeckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    ischeck=b;
            }
        });
        loginPresenter.readtoPreferences();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                if (login_check()) {
                    loadingIndicatorView.setVisibility(View.VISIBLE);
                    loginPresenter.login();
                }

            break;
            case R.id.register:
                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .setCustomAnimations(R.anim.in_from_left,R.anim.in_from_right,R.anim.out_from_left,R.anim.out_from_right)
                        .replace(R.id.login_login,new RegisterFragment2())
                        .commit();
                break;
        }
    }
    private boolean login_check(){
        if (Tools.checknetwork(this)) {
            if(TextUtils.isEmpty(textInputEditTextaccount.getText())){
                textInputEditTextaccount.setError("请输入手机号码！");
            }else if (TextUtils.isEmpty(textInputEditTextpassword.getText())){
                textInputEditTextpassword.setError("请输入密码！");
            }else if (!RegexUtil.checkmobilephone(textInputEditTextaccount.getText().toString())){
                textInputEditTextaccount.setError( "手机号码格式不正确！");
            }else{
                return  true;
            }
        }
        return false;
    }
    public void loginsuccess(){
        loginPresenter.writetoPreferences(ischeck,textInputEditTextaccount.getText().toString(),textInputEditTextpassword.getText().toString());
        loadingIndicatorView.setVisibility(View.GONE);
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

        finish();
    }
    public void autoaccount(String account,String password,boolean ischeck){
        textInputEditTextaccount.setText(account);
        textInputEditTextpassword.setText(password);
        ischeckbox.setChecked(ischeck);
    }


}
