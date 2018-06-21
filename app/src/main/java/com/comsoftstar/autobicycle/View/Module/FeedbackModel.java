package com.comsoftstar.autobicycle.View.Module;

import com.comsoftstar.autobicycle.App.Single;
import com.comsoftstar.autobicycle.Interface.API;
import com.comsoftstar.autobicycle.Model.NetWork.http.HttpClient;

import java.util.Map;

/**
 * Created by SJ on 2018/6/21.
 */
//用户反馈
public class FeedbackModel {
    HttpClient httpClient= Single.getInstance().httpClient;
    public FeedbackModel(){

    }
    //用户反馈
    public void feedbackRecord(String loginName){
        String opType="FeedbackRecord";
        httpClient.service(API.XYService).FeedbackRecord(opType,loginName);
    }
    //反馈保存
    public void saveFeedback(Map<String, String> params){
        params.put("opType","saveFeedback");
        httpClient.service(API.XYService).saveFeedback(params);
    }

}
