package com.comsoftstar.autobicycle.Model.NetWork.NetGet;


import com.comsoftstar.autobicycle.Model.Bean.CallBack.Login.LoginResult;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Main.FeedBack;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.R_Result;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Register.SalePoint;

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
    @GET("AppHandler.ashx")//opType="getVeriCode"  veriType="注册"
    Call<R_Result>VeriCode(@Query("opType") String opType,@Query("loginName") String loginName,@Query("veriType") String veriType);

    /**
     * 获取营业网点
     */
    @GET("AppHandler.ashx")//opType="getSalePoint"
    Call<List<SalePoint>>SalePoint(@Query("opType") String opType);

    /**
     * 注册
     */
    @GET("AppHandler.ashx")//opType="Register"
    Call<R_Result>Register(@QueryMap Map<String, String> options);


    /**
     * 登录
     */
    @GET("AppHandler.ashx")//opType=login
    Call<List<LoginResult>>Login(@Query("opType") String opType, @Query("loginName")String loginName, @Query("loginType")String loginType, @Query("loginCode")String loginCode);

    /**
     * 报修记录
     */
    @GET("AppHandler.ashx")// opType=RepairRecord
    Call<List<LoginResult>>RepairRecord(@Query("opType") String opType, @Query("loginName")String loginName);

    /**
     * 报修保存
     */
    @GET("AppHandler.ashx")// opType=saveRecord
    Call<List<LoginResult>>saveRecord(@QueryMap Map<String, String> options);

    /**
     * 用户反馈
     */
    @GET("AppHandler.ashx")// opType=FeedbackRecord
    Call<List<FeedBack>>FeedbackRecord(@Query("opType") String opType, @Query("loginName")String loginName);

    /**
     * 反馈保存
     */
    @GET("AppHandler.ashx")// opType=saveFeedback
    Call<R_Result>saveFeedback(@QueryMap Map<String, String> params);



    //region 模版
//    @GET("/group/{id}/users")
//    List<User> groupList(@Path("id") int groupId);
//
//    @GET("/group/{id}/users")
//    List<User> groupList(@Path("id") int groupId, @Query("sort") String sort);
//
//    @GET("/group/{id}/users")
//    List<User> groupList(@Path("id") int groupId, @QueryMap Map<String, String> options);
//
//    @POST("/users/new")
//    void createUser(@Body User user, Callback<User> cb);//Callback参数下面讲解
//
//    @Headers("Cache-Control: max-age=640000")
//    @GET("/widget/list")
//    List<Widget> widgetList();
//
//    @Multipart//多部分数组形式提交体
//    @POST("Ashx/Snapshot.ashx")
//    Call<R_Result> uploadPictures(@Part() MultipartBody.Part  part);
//
//    @Multipart//多部分列表形式提交体
//    @POST("AppHandler_Test.ashx")
//    Call<ResponseBody> modifyy(@PartMap Map<String, RequestBody> map, @Part List<MultipartBody.Part> parts);
//
//    @FormUrlEncoded//表单提交
//    @POST("/user/edit")
//    Call<ResponseBody> updateUser(@Field("first_name") String first, @Field("last_name") String last);
//
//    @Headers({"Content-type:application/json,"Accept: application/json"})
//     @POST("/api/v1/trade/HasAccount.json")
//     Call<BaseResponse> createCommit(@Body RequestBody route);
    //endregion
}

