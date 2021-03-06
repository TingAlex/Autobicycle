package com.comsoftstar.autobicycle.View.Module;

import com.comsoftstar.autobicycle.App.Single;
import com.comsoftstar.autobicycle.Interface.API;
import com.comsoftstar.autobicycle.Interface.CallBack;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.R_Result;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Register.SalePoint;
import com.comsoftstar.autobicycle.Model.NetWork.http.HttpClient;
import com.comsoftstar.autobicycle.Model.NetWork.http.MyError;
import com.comsoftstar.autobicycle.Model.NetWork.http.ResponseHandler;
import com.comsoftstar.autobicycle.Util.Logs;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by SJ on 2018/6/25.
 */

public class RegisterModel {
    static String tag = "NetUtil";
    static HttpClient httpClient = Single.getInstance().httpClient;

    //region 单例
    public static RegisterModel getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RegisterModel INSTANCE = new RegisterModel();
    }

    private RegisterModel() {

    }
    //endregion

    /**
     * 验证码
     */
    public void VeriCode(String loginName, String veriType, final CallBack<R_Result> callBack) {
        String opType = "getVeriCode";

        Call<R_Result> call = httpClient.service(API.XYService).VeriCode(opType, loginName, veriType);
        httpClient.request(call, new ResponseHandler() {
            @Override
            public void onSuccess(Object o) {
                callBack.success((R_Result) o);
            }

            @Override
            public void onFailure(MyError e) {

            }
        });
    }

    /**
     * 注册
     *
     * @param params
     * @param callBack
     */
    public void Register(Map<String, String> params, final CallBack<R_Result> callBack) {
        params.put("opType", "Register");
        Call<R_Result> call = httpClient.service(API.XYService).Register(params);
        httpClient.request(call, new ResponseHandler() {
            @Override
            public void onSuccess(Object o) {
                // Toast.makeText(context, ((R_Result)o).getResult(), Toast.LENGTH_SHORT).show();
                callBack.success(((R_Result) o));
            }

            @Override
            public void onFailure(MyError e) {
                callBack.faile(e.getMessage());

            }

        });
    }


}
