package com.comsoftstar.autobicycle.View.Presenter;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.comsoftstar.autobicycle.App.Value;
import com.comsoftstar.autobicycle.Business.StaticData;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Login.LoginResult;
import com.comsoftstar.autobicycle.Model.NetWork.http.MyError;
import com.comsoftstar.autobicycle.View.Module.LoginModule;
import com.comsoftstar.autobicycle.View.Login.View.LoginActivity;
import com.comsoftstar.autobicycle.View.Login.Interface.Login_inteface;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/9/28.
 */

public class LoginPresenter implements LoginModule.ILoginPresenter {
    private static LoginActivity activity;
    private Login_inteface login_inteface;
    private LoginModule loginModule = new LoginModule(this);

    public LoginPresenter(LoginActivity activity) {
        this.login_inteface = activity;
        this.activity = activity;
    }

    //region 功能实现


    /**
     * 登录
     */
    public void login(String loginName, String Type, String loginCode) {
        loginModule.login(loginName, Type, loginCode);
    }


    /**
     * 发送验证码
     *
     * @param loginName
     */
    public void sendVerCode(String loginName) {
        loginModule.getVerCode(loginName);

    }

    //endregion

    /**
     * 配置写入
     */
    public void writetoPreferences(boolean b, String account, String password) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(Value.PATH, MODE_PRIVATE).edit();
        if (b) {
            editor.putString("accound", account);
            editor.putString("password", password);
            editor.putBoolean("ischeck", b);
            editor.commit();
        } else {
            editor.putString("accound", account);
            editor.putString("password", "");
            editor.putBoolean("ischeck", b);
            editor.commit();
        }
    }

    /**
     * 配置读取
     */
    public void readtoPreferences() {
        SharedPreferences pref = activity.getSharedPreferences(Value.PATH, MODE_PRIVATE);
        String account = pref.getString("accound", "");
        String password = pref.getString("password", "");
        boolean ischeck = pref.getBoolean("ischeck", false);
        if (!TextUtils.isEmpty(account)) {
            login_inteface.autoaccount(account, password, ischeck);
        }
    }

    //region LoginModule.ILoginPresenter 接口实现


    /**
     * 验证码发送信息返回
     *
     * @param value
     */
    @Override
    public void verCodeMsg(String value) {
        login_inteface.getVerCodeMsg(value);
    }

    //登陆结果返回
    @Override
    public void LoginResult(List<LoginResult> result) {
        //Todo:数据后期处理
        StaticData.loginResults = result;
        login_inteface.loginsuccess();
    }

    //登陆报错
    @Override
    public void LoginResultFaile(MyError e) {
        login_inteface.loginfaile(e.getMessage());
    }
    //endregion
}
