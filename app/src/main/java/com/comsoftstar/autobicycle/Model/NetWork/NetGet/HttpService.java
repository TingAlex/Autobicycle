package com.comsoftstar.autobicycle.Model.NetWork.NetGet;


import com.comsoftstar.autobicycle.Model.Bean.CallBack.Login.LoginResult;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Main.FeedBackBean;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Main.RepairRecordBean;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.R_Result;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Register.SalePoint;
import com.comsoftstar.autobicycle.Model.Bean.HomePage;
import com.comsoftstar.autobicycle.Model.Bean.WorkRecords;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByDay;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByMonth;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByWeek;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2016/5/9 0009.
 */
public interface HttpService {


    /**
     * 验证码
     */
    @GET("AppHandler.ashx")
//opType="getVeriCode"  veriType="注册"
    Call<R_Result> VeriCode(@Query("opType") String opType, @Query("loginName") String loginName, @Query("veriType") String veriType);

    /**
     * 获取营业网点
     */
    @GET("AppHandler.ashx")
//opType="getSalePoint"
    Call<List<SalePoint>> SalePoint(@Query("opType") String opType);

    /**
     * 注册
     */
    @GET("AppHandler.ashx")
//opType="Register"
    Call<R_Result> Register(@QueryMap Map<String, String> options);


    /**
     * 登录
     */
    @GET("AppHandler.ashx")
//opType=login
    Call<List<LoginResult>> Login(@Query("opType") String opType, @Query("loginName") String loginName, @Query("loginType") String loginType, @Query("loginCode") String loginCode);

    /**
     * 报修记录
     */
    @GET("AppHandler.ashx")
// opType=RepairRecord
    Call<List<RepairRecordBean>> RepairRecord(@Query("opType") String opType, @Query("loginName") String loginName);

    /**
     * 报修保存
     */
    @GET("AppHandler.ashx")
// opType=saveRecord
    Call<R_Result> saveRecord(@QueryMap Map<String, String> options);

    /**
     * 用户反馈
     */
    @GET("AppHandler.ashx")
// opType=FeedbackRecord
    Call<List<FeedBackBean>> FeedbackRecord(@Query("opType") String opType, @Query("loginName") String loginName);

    /**
     * 反馈保存
     */
    @GET("AppHandler.ashx")
// opType=saveFeedback
    Call<R_Result> saveFeedback(@QueryMap Map<String, String> params);

    /**
     * 首页
     */
    @GET("AppHandler.ashx")
// opType=HomePage
    Call<List<HomePage>> homePage(@QueryMap Map<String, String> params);

    /**
     * 行车历史
     */
    @GET("AppHandler.ashx")
// opType=WorkRecords
    Call<List<WorkRecords>> workRecords(@Query("opType") String opType, @Query("cfgID") String cfgID);

    /**
     * 骑行统计按天
     */
    @GET("AppHandler.ashx")
// opType=WorkStatisticsByDay
    Call<List<WorkStatisticsByDay>> workStatisticsByDay(@Query("opType") String opType, @Query("cfgID") String cfgID);


    /**
     * 骑行统计按周
     */
    @GET("AppHandler.ashx")
// opType=WorkStatisticsByWeek
    Call<List<WorkStatisticsByWeek>> workStatisticsByWeek(@Query("opType") String opType, @Query("cfgID") String cfgID);


    /**
     * 骑行统计按月
     */
    @GET("AppHandler.ashx")
// opType=WorkStatisticsByMonth
    Call<List<WorkStatisticsByMonth>> workStatisticsByMonth(@Query("opType") String opType, @Query("cfgID") String cfgID);

}

