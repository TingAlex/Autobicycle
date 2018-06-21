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

    /**
     * 验证码
     */
    public  void VeriCode( String loginName,String veriType, final CallBack<R_Result> callBack){
        String opType="getVeriCode";

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

    /**
     * 注册
     * @param params
     * @param callBack
     */
    public  void Register( Map<String,String> params, final CallBack<R_Result> callBack){
        params.put("opType","Register");
        Call<R_Result> call=httpClient.service(API.XYService).Register(params);
        httpClient.request(call, new ResponseHandler() {
            @Override
            public void onSuccess(Object o) {
               // Toast.makeText(context, ((R_Result)o).getResult(), Toast.LENGTH_SHORT).show();
                callBack.success(((R_Result)o));
            }

            @Override
            public void onFailure(MyError e) {
                callBack.faile(e.getMessage());

            }

        });
    }

    /**
     * 营业网点
     * @param callBack
     */
    public  void SalePoint( final CallBack<SalePoint> callBack){
        String opType="getSalePoint";
        Call<SalePoint> call=httpClient.service(API.XYService).SalePoint(opType);
        httpClient.request(call, new ResponseHandler() {
            @Override
            public void onSuccess(Object o) {
                Logs.d(tag,((SalePoint)o).getText());
                callBack.success((SalePoint)o);
                // Toast.makeText(context, ((R_Result)o).getResult(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure( MyError e) {
                Logs.e(tag,e.getMessage());
                callBack.faile(e.getMessage());
            }

        });
    }




}
