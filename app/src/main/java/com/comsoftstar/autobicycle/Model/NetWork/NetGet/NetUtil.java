package com.comsoftstar.autobicycle.Model.NetWork.NetGet;


import com.comsoftstar.autobicycle.App.Single;
import com.comsoftstar.autobicycle.Interface.API;
import com.comsoftstar.autobicycle.Interface.CallBack;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Register.R_Result;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Register.SalePoint;
import com.comsoftstar.autobicycle.Model.NetWork.http.MyError;
import com.comsoftstar.autobicycle.Model.NetWork.http.HttpClient;
import com.comsoftstar.autobicycle.Model.NetWork.http.ResponseHandler;
import com.comsoftstar.autobicycle.Util.Logs;

import java.util.Map;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/10/16.
 */

public class NetUtil {
    static String tag="NetUtil";
    static HttpClient httpClient= Single.getInstance().httpClient;

    //region 单例
    public static NetUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final NetUtil INSTANCE = new NetUtil();
    }

    private NetUtil() {

    }
    //endregion

    //region 注册模块

    //验证码
    public  void VeriCode( String loginName, final CallBack<R_Result> callBack){
        String opType="getVeriCode";
        String veriType="注册";
        Call<R_Result> call=httpClient.service(API.XYService).VeriCode(opType,loginName,veriType);
        httpClient.request(call, new ResponseHandler() {
            @Override
            public void onSuccess(Object o) {
                callBack.success((R_Result)o);
            }

            @Override
            public void onFailure(MyError e) {

            }
        });
    }

    //注册
    public  void Register( Map<String,String> parameter, final CallBack<R_Result> callBack){

        Call<R_Result> call=httpClient.service(API.XYService).Register(parameter);
        httpClient.request(call, new ResponseHandler() {
            @Override
            public void onSuccess(Object o) {
                Logs.d(tag,((R_Result)o).getResult());
               // Toast.makeText(context, ((R_Result)o).getResult(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(MyError e) {
                Logs.e(tag,e.getMessage());
            }

        });
    }

    //营业网点
    public  void SalePoint(String getSalePoint, final CallBack<SalePoint> callBack){
        String opType="getSalePoint";
        Call<SalePoint> call=httpClient.service(API.XYService).SalePoint(opType);
        httpClient.request(call, new ResponseHandler() {
            @Override
            public void onSuccess(Object o) {
                Logs.d(tag,((SalePoint)o).getText());
                // Toast.makeText(context, ((R_Result)o).getResult(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure( MyError e) {
                Logs.e(tag,e.getMessage());
            }

        });
    }

    //endregion


}
