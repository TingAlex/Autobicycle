package com.comsoftstar.autobicycle.View.Module;


import com.comsoftstar.autobicycle.App.Single;
import com.comsoftstar.autobicycle.Interface.API;
import com.comsoftstar.autobicycle.Interface.CallBack;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Main.FeedBackBean;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.R_Result;
import com.comsoftstar.autobicycle.Model.NetWork.http.HttpClient;
import com.comsoftstar.autobicycle.Model.NetWork.http.MyError;
import com.comsoftstar.autobicycle.Model.NetWork.http.ResponseHandler;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by SJ on 2018/6/21.
 */
//用户反馈
public class FeedbackModel {
    HttpClient httpClient = Single.getInstance().httpClient;

    public FeedbackModel() {

    }

    /**
     * 用户反馈
     *
     * @param loginName
     * @param callBack
     */
    public void feedbackRecord(String loginName, final CallBack<List<FeedBackBean>> callBack) {
        String opType = "FeedbackRecord";
        Call<List<FeedBackBean>> call = httpClient.service(API.XYService).FeedbackRecord(opType, loginName);

        httpClient.request(call, new ResponseHandler() {
            @Override
            public void onSuccess(Object o) {
                callBack.success((List<FeedBackBean>) o);
            }

            @Override
            public void onFailure(MyError e) {
                callBack.faile(e.getMessage());

            }
        });

    }


    /**
     * 反馈保存
     *
     * @param params
     * @param callBack
     */
    public void saveFeedback(Map<String, String> params, final CallBack<R_Result> callBack) {
        params.put("opType", "saveFeedback");
        Call<R_Result> call = httpClient.service(API.XYService).saveFeedback(params);

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

}
