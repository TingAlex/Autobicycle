package com.comsoftstar.autobicycle.View.Module;

import com.comsoftstar.autobicycle.App.Single;
import com.comsoftstar.autobicycle.Interface.API;
import com.comsoftstar.autobicycle.Model.NetWork.http.HttpClient;

import java.util.Map;

/**
 * Created by SJ on 2018/6/21.
 */
//报修
public class RepairModel {
    HttpClient httpClient= Single.getInstance().httpClient;
    public RepairModel(){

    }
    //报修记录
    public void repairRecord(String loginName){
        String opType="RepairRecord";
        httpClient.service(API.XYService).RepairRecord(opType,loginName);
    }
    //报修保存
    public void saveRecord(Map<String, String> params){
        params.put("opType","saveRecord");
        httpClient.service(API.XYService).saveRecord(params);
    }

}
