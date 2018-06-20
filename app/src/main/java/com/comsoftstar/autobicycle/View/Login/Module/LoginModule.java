package com.comsoftstar.autobicycle.View.Login.Module;


import com.comsoftstar.autobicycle.App.Single;
import com.comsoftstar.autobicycle.Interface.API;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Login.LoginResult;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Register.R_Result;
import com.comsoftstar.autobicycle.Model.NetWork.http.Error;
import com.comsoftstar.autobicycle.Model.NetWork.http.HttpClient;
import com.comsoftstar.autobicycle.Model.NetWork.http.ResponseHandler;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/9/28.
 */

public class LoginModule {
    HttpClient httpClient= Single.getInstance().httpClient;

    //region 单例
    public LoginModule(){
    }

    public static LoginModule getInstance() {
        return SingletonHolder.INSTANCE;
    }
    private static class SingletonHolder {
        private static final LoginModule INSTANCE = new LoginModule();
    }
    //endregion

    public  interface Login<T>{
        void result(T s);
    }
    //登录
    public  void login(String loginName,String loginType,String loginCode,final Login<String> login){
            String opType="login";
            Call<String> call=httpClient.service(API.XYService).Login(opType,loginName,loginType,loginCode);
            httpClient.request(call, new ResponseHandler() {
                @Override
                public void onSuccess(Object o) {
                    login.result((String)o);
                }

                @Override
                public void onFailure(int code, Error e) {

                }

                @Override
                public void onFailure(int code, String e) {

                }
            });
       // login.result("qwe");
    }


}
