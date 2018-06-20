package com.comsoftstar.autobicycle.View.Login.Presenter;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.comsoftstar.autobicycle.Interface.Value;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Login.LoginResult;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Register.R_Result;
import com.comsoftstar.autobicycle.View.Login.Module.LoginModule;
import com.comsoftstar.autobicycle.View.Login.View.LoginActivity;
import com.comsoftstar.autobicycle.View.Login.View.Login_inteface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

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
    //登录
    public  void login(String loginName,String Type,String loginCode){
        LoginModule.getInstance().login(loginName, Type, loginCode, new LoginModule.Login<String>() {
            @Override
            public void result(String s) {
                Gson gson=new Gson();
                try{
                    List<LoginResult> loginResults = gson.fromJson(s, new TypeToken<List<LoginResult>>(){}.getType());
                    //Todo:数据后期处理
                    login_inteface.loginsuccess();
                }catch (Exception e){
                    try{
                        R_Result result = gson.fromJson(s, R_Result.class);
                        login_inteface.loginfaile(result.getResult());
                    }catch (Exception e2){
                        login_inteface.loginfaile(e2.getMessage());
                    }
                }
            }
        });
    }
    //配置写入
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
    //配置读取
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
