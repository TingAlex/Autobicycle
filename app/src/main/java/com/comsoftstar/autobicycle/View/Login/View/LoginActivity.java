package com.comsoftstar.autobicycle.View.Login.View;

import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.comsoftstar.autobicycle.Base.BaseActivity;
import com.comsoftstar.autobicycle.Control.CountDownButton;
import com.comsoftstar.autobicycle.Control.SuperInputEditText;
import com.comsoftstar.autobicycle.Interface.Value;
import com.comsoftstar.autobicycle.Util.RegexUtil;
import com.comsoftstar.autobicycle.Util.Tools;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.View.Login.Interface.Login_inteface;
import com.comsoftstar.autobicycle.View.Login.Presenter.LoginPresenter;
import com.comsoftstar.autobicycle.View.Main.Activity.MainActivity;
import com.comsoftstar.autobicycle.databinding.ActivityLogin2Binding;

import com.wang.avi.AVLoadingIndicatorView;
public class LoginActivity extends BaseActivity<ActivityLogin2Binding> implements View.OnClickListener,Login_inteface {
public SuperInputEditText textInputEditTextaccount,textInputEditTextpassword;
    private TextView register;
    private Button login;
    private LoginPresenter loginPresenter;
    private CheckBox ischeckbox;            //记住密码
    private boolean ischeck;                //是否记住密码
    private AVLoadingIndicatorView loadingIndicatorView;  //加载动画
    private ActivityLogin2Binding mbinding;
    private String loginType=Value.Login.PASSWORD.getValue();

    @Override
    public int setLayoutId() {
        return R.layout.activity_login2;
    }
    @Override
    public void initView(ActivityLogin2Binding binding) {
        loginPresenter=new LoginPresenter(this);
        mbinding=binding;
        // 初始化
        initUI();
    }
    private void initUI(){
        textInputEditTextaccount=(SuperInputEditText)findViewById(R.id.login_account);
        textInputEditTextpassword=(SuperInputEditText)findViewById(R.id.login_password);
        loadingIndicatorView=(AVLoadingIndicatorView)findViewById(R.id.login_load);
        register=(TextView)findViewById(R.id.register);
        register.setOnClickListener(this);
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(this);
        ischeckbox=(CheckBox)findViewById(R.id.login_ischeck);
        mbinding.loginBtnSend.setinterface(new CountDownButton.ICountDownButton() {
            @Override
            public void message(String msg) {
                toast(msg);
            }
        });
        //验证码发送
        mbinding.loginBtnSend.setOnClickListener(mbinding.loginAccount, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.sendVerCode(mbinding.loginAccount.getText().toString());
            }
        });
        //记住密码单选
        ischeckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    ischeck=b;
            }
        });
        //验证码登录单选
        mbinding.loginIsvercode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mbinding.layoutVercode.setVisibility(View.VISIBLE);
                    mbinding.layoutPassword.setVisibility(View.GONE);
                    loginType=Value.Login.VERCODE.getValue();
                }else{
                    mbinding.layoutPassword.setVisibility(View.VISIBLE);
                    mbinding.layoutVercode.setVisibility(View.GONE);
                    loginType=Value.Login.PASSWORD.getValue();
                }
            }
        });
        loginPresenter.readtoPreferences();
        //android8.0更新适配
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (getPackageManager().canRequestPackageInstalls()) {
                //TODO：检查更新待处理
                //UpdataDownLoad.getInstance(this).dowmload("","");
            } else {
                // 申请权限。
                startInstallPermissionSettingActivity();
            }
        }else{
           // UpdataDownLoad.getInstance(this).dowmload("","");
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity() {
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        startActivityForResult(intent, 10086);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //登陆
            case R.id.login:
                String code = "";
                if (loginType.equals(Value.Login.PASSWORD.getValue())){

                    if (login_check(true)) {

                        loadingIndicatorView.setVisibility(View.VISIBLE);
                        code=textInputEditTextpassword.getText().toString();
                        loginPresenter.login(textInputEditTextaccount.getText().toString(), loginType,code);
                    }
                }else if (loginType.equals(Value.Login.VERCODE.getValue())){
                    if (login_check(false)){
                        loadingIndicatorView.setVisibility(View.VISIBLE);
                        code=mbinding.editVercode.getText().toString();
                        loginPresenter.login(textInputEditTextaccount.getText().toString(), loginType,code);
                    }
                }

                break;
            //注册
            case R.id.register:
                getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .setCustomAnimations(R.anim.in_from_left,R.anim.in_from_right,R.anim.out_from_left,R.anim.out_from_right)
                        .replace(R.id.login_login,new RegisterFragment2())
                        .commit();
                break;


        }
    }

    //登录检查
    private boolean login_check(boolean b){
        if (Tools.checknetwork(this)) {
            if(TextUtils.isEmpty(textInputEditTextaccount.getText())){
                textInputEditTextaccount.setError("请输入手机号码！");
            }else if (TextUtils.isEmpty(textInputEditTextpassword.getText()) && b){
                textInputEditTextpassword.setError("请输入密码！");
            }else if (TextUtils.isEmpty(mbinding.editVercode.getText()) && !b){
                toast("请输入验证码！");
            }else if (!RegexUtil.checkmobilephone(textInputEditTextaccount.getText().toString())){
                textInputEditTextaccount.setError( "手机号码格式不正确！");
            }else{
                return  true;
            }
        }
        return false;
    }

    //region Login_inteface接口实现

    //登录成功
    @Override
    public void loginsuccess(){
        //账号密码记住
        loginPresenter.writetoPreferences(ischeck,textInputEditTextaccount.getText().toString(),textInputEditTextpassword.getText().toString());
        loadingIndicatorView.setVisibility(View.GONE);
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    //自动记住账号密码
    @Override
    public void autoaccount(String account,String password,boolean ischeck){
        textInputEditTextaccount.setText(account);
        textInputEditTextpassword.setText(password);
        ischeckbox.setChecked(ischeck);
    }
    //登录失败
    @Override
    public void loginfaile(String result) {
        loadingIndicatorView.setVisibility(View.GONE);
        toast(result);
    }
    //验证码发送回调
    @Override
    public void getVerCodeMsg(String msg) {
        toast(msg);
    }
    //endregion

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10086) {
            //TODO:检查更新待处理
            //UpdataDownLoad.getInstance(this).dowmload("","");
        }
    }

}
