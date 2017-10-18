package com.comsoftstar.autobicycle.Public.NetWork.http;


import com.comsoftstar.autobicycle.Main.CK.ViewModel.Fragment1_Item;

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
   /* @GET("Login/validate")
    Call<User> login(@QueryMap Map<String, String> params);

    //获取用户
    @GET("Operate/alluser")
    Call<List<User>> getUser();

    //获取所有库位
    @GET("Operate/devicePlace")
    Call<List<Place>> getAllPlace();

    //获取所有维修原因
    @GET("Operate/allRepairReason")
    Call<List<ReasonBen>> getAllRepairReason();

    //获取所有报废原因
    @GET("Operate/allScrapReason")
    Call<List<ReasonBen>> getAllScrapReason();

    //保养
    @GET("Operate/keepUp")
    Call<ResponseBody> keepUp(@Query("qrcode") String qrcode, @Query("reason") String reason);

    //入库
    @GET("Operate/putin")
    Call<ResponseBody> putIn(@QueryMap Map<String, String> params);

    //创建警告
    @GET("Operate/createPatrol")
    Call<ResponseBody> createPatrol(@QueryMap Map<String, String> params);

    //解决警告
    @GET("Operate/finishPatrol")
    Call<ResponseBody> finishPatrol(@Query("patrol_id") String id);

    //警告列表
    @GET("Operate/patrolList")
    Call<List<Patrol>> patrolList();


    //巡查设备
    @GET("Operate/inspectDevice")
    Call<Device> inspectDevice(@Query("qrcode") String qrcode);

    //设备详情
    @GET("Operate/getDeviceInfo")
    Call<Device> getDeviceInfo(@Query("qrcode") String qrcode);

    //维修设备
    @GET("Operate/repaire")
    Call<ResponseBody> repaire(@Query("qrcode") String qrcode, @Query("operate_type") String operate_type, @Query("reason") String reason);

    //任务列表
    @GET("Operate/taskList")
    Call<List<Task>> taskList();

    //保养设备列表
    @GET("Operate/keepUpDevices")
    Call<List<KeepTask>> keepTaskList();

    //保养原因列表
    @GET("Operate/allKeepReason")
    Call<List<ReasonBen>> keepReasonList();

    //保养设备信息
    @GET("Operate/getKeepDeviceInfo")
    Call<KeepTask> getKeepDeviceInfo(@Query("qrcode") String qrcode);

    //任务数量
    @GET("Operate/numberOfTask")
    Call<Map<String,String>> numberOfTask();

    //任务详细
    @GET("Operate/taskDetail")
    Call<TaskDetail> taskDetail(@Query("id") String id);

    //开始盘点
    @GET("Operate/beginTask")
    Call<Map<String,String>> beginTask(@Query("id") String place_id);

    //结束盘点
    @GET("Operate/finishTask")
    Call<ResponseBody> finishTask(@Query("id") String task_id);

    //盘点
    @GET("Operate/checkDevice")
    Call<ResponseBody> checkDevice(@Query("place_id") String place_id, @Query("task_id") String taskid, @Query("qr_code") String qr_code);*/


}
