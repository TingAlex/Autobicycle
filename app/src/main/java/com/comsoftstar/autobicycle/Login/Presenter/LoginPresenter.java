package com.comsoftstar.autobicycle.Login.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Toast;

import com.comsoftstar.autobicycle.Login.Module.LoginModule;
import com.comsoftstar.autobicycle.Login.View.LoginActivity;
import com.comsoftstar.autobicycle.Login.View.Login_inteface;
import com.comsoftstar.autobicycle.Main.Main.MainActivity;
import com.comsoftstar.autobicycle.Public.Value;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/9/28.
 */

public class LoginPresenter {
    private static LoginActivity activity;
    private Login_inteface login_inteface;
    public LoginPresenter(LoginActivity activity){
        this.login_inteface=activity;
        this.activity=activity;
    }
    public  void login(){
        new LoginModule().login(new LoginModule.Login() {
            @Override
            public void result(String s) {
                if (!TextUtils.isEmpty(s))
                login_inteface.loginsuccess();
            }
        });
    }
    public void writetoPreferences(boolean b,String account ,String password){
        SharedPreferences.Editor editor =activity.getSharedPreferences(Value.PATH, MODE_PRIVATE).edit();
        if (b){
            editor.putString("accound", account);
            editor.putString("password", password);
            editor.putBoolean("ischeck",b);
            editor.commit();
        }else{
            editor.putString("accound", account);
            editor.putString("password", "");
            editor.putBoolean("ischeck",b);
            editor.commit();
        }
    }
    public void readtoPreferences(){
        SharedPreferences pref =activity.getSharedPreferences(Value.PATH, MODE_PRIVATE);
        String account = pref.getString("accound", "");
        String password = pref.getString("password", "");
        boolean ischeck=pref.getBoolean("ischeck",false);
        if (!TextUtils.isEmpty(account)){
            login_inteface.autoaccount(account,password,ischeck);
        }
    }


}
