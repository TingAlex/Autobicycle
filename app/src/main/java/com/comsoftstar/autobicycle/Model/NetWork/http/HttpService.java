package com.comsoftstar.autobicycle.Model.NetWork.http;


import com.comsoftstar.autobicycle.Model.Bean.Fragment1_Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/5/9 0009.
 */
public interface HttpService {


    //登录
    @GET("AppHandler.ashx")
    Call<List<Fragment1_Item>>result(@Query("opType") String opType, @Query("loginName")String loginName, @Query("password")String password);

    //短信接口


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
//    Call<Result> uploadPictures(@Part() MultipartBody.Part  part);
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
