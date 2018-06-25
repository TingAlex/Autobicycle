package com.comsoftstar.autobicycle.View.Module;


import com.comsoftstar.autobicycle.App.Single;
import com.comsoftstar.autobicycle.Interface.API;
import com.comsoftstar.autobicycle.Interface.CallBack;
import com.comsoftstar.autobicycle.App.Value;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Login.LoginResult;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.R_Result;
import com.comsoftstar.autobicycle.Model.NetWork.http.MyError;
import com.comsoftstar.autobicycle.Model.NetWork.http.HttpClient;
import com.comsoftstar.autobicycle.Model.NetWork.http.ResponseHandler;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/9/28.
 */

public class LoginModule {
    HttpClient httpClient= Single.getInstance().httpClient;
    ILoginPresenter iLoginPresenter;
    public LoginModule(ILoginPresenter iLoginPresenter){
        this.iLoginPresenter=iLoginPresenter;
    }

    public interface ILoginPresenter{
        void verCodeMsg(String value);
        void LoginResult(List<LoginResult> result);
        void LoginResultFaile(MyError e);

    }

    public  interface Login<T>{
        void result(T s);
        void faile(MyError e);
    }

    /**
     * 登录
     */
    public  void login(String loginName,String loginType,String loginCode){
            String opType="login";
            Call<List<LoginResult>> call=httpClient.service(API.XYService).Login(opType,loginName,loginType,loginCode);
            httpClient.request(call, new ResponseHandler() {
                @Override
                public void onSuccess(Object o) {
                    iLoginPresenter.LoginResult((List<LoginResult>)o);

                }

                @Override
                public void onFailure(MyError e) {
                    iLoginPresenter.LoginResultFaile(e);
                }


            });
       // login.result("qwe");
    }

    /**
     * 验证码
     * @param phone
     */
    public void getVerCode(String phone){
        RegisterModel.getInstance().VeriCode(phone, Value.VerCode.LOGIN.getValue(), new CallBack<R_Result>() {
            @Override
            public void success(R_Result result) {
                iLoginPresenter.verCodeMsg(result.getResult());
            }
            @Override
            public void faile(String s) {
                iLoginPresenter.verCodeMsg(s);
            }
        });
    }



}
