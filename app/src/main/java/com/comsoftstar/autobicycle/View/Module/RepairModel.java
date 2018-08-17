package com.comsoftstar.autobicycle.View.Module;

import com.comsoftstar.autobicycle.App.Single;
import com.comsoftstar.autobicycle.Interface.API;
import com.comsoftstar.autobicycle.Interface.CallBack;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Main.RepairRecordBean;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.R_Result;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Register.SalePoint;
import com.comsoftstar.autobicycle.Model.NetWork.http.HttpClient;
import com.comsoftstar.autobicycle.Model.NetWork.http.MyError;
import com.comsoftstar.autobicycle.Model.NetWork.http.ResponseHandler;
import com.comsoftstar.autobicycle.Util.Logs;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.comsoftstar.autobicycle.View.Module.RegisterModel.tag;

/**
 * Created by SJ on 2018/6/21.
 */
//报修
public class RepairModel {
    HttpClient httpClient = Single.getInstance().httpClient;

    public RepairModel() {

    }

    /**
     * 报修记录
     *
     * @param loginName
     */
    public void repairRecord(String loginName, final CallBack<List<RepairRecordBean>> callBack) {
        String opType = "RepairRecord";
        Call<List<RepairRecordBean>> call = httpClient.service(API.XYService).RepairRecord(opType, loginName);
        httpClient.request(call, new ResponseHandler() {
            @Override
            public void onSuccess(Object o) {
                callBack.success((List<RepairRecordBean>) o);
            }

            @Override
            public void onFailure(MyError e) {
                callBack.faile(e.getMessage());
            }
        });

    }

    /**
     * 报修保存
     *
     * @param params
     */
    public void saveRecord(Map<String, String> params, final CallBack<R_Result> callBack) {
        params.put("opType", "saveRecord");
        final Call<R_Result> call = httpClient.service(API.XYService).saveRecord(params);
        httpClient.request(call, new ResponseHandler() {
            @Override
            public void onSuccess(Object o) {
                callBack.success((R_Result) o);
            }

            @Override
            public void onFailure(MyError e) {
                callBack.faile(e.getMessage());
            }
        });
    }

    /**
     * 营业网点
     *
     * @param callBack
     */
    public void SalePoint(final CallBack<List<SalePoint>> callBack) {
        String opType = "getSalePoint";
        Call<List<SalePoint>> call = httpClient.service(API.XYService).SalePoint(opType);
        httpClient.request(call, new ResponseHandler() {
            @Override
            public void onSuccess(Object o) {
                callBack.success((List<SalePoint>) o);
                // Toast.makeText(context, ((R_Result)o).getResult(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(MyError e) {

                callBack.faile(e.getMessage());
            }

        });
    }
}
