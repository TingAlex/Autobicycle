package com.comsoftstar.autobicycle.View.Module;

import android.content.Context;


import com.comsoftstar.autobicycle.Interface.API;
import com.comsoftstar.autobicycle.Model.Bean.HomePage;
import com.comsoftstar.autobicycle.Model.Bean.WorkRecords;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByDay;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByMonth;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByWeek;
import com.comsoftstar.autobicycle.Model.NetWork.http.HttpClient;
import com.comsoftstar.autobicycle.Model.NetWork.http.MyError;
import com.comsoftstar.autobicycle.Model.NetWork.http.ResponseHandler;
import com.comsoftstar.autobicycle.View.Interface.IPresenter.IPagePresenter;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Author       : SJ
 * Time         : 2018/8/4  12:49
 * Description  : PageModal
 */

public class PageModal {
    private Context context;
    private IPagePresenter iPagePresenter;
    public PageModal(Context context,IPagePresenter iPagePresenter){
        this.context=context;
        this.iPagePresenter=iPagePresenter;
    }

    public void getHomePage(Map maps){
        maps.put("opType","HomePage");
        HttpClient httpClient=new HttpClient(context);
        Call<List<HomePage>> call =httpClient.service(API.XYService).homePage(maps);
        httpClient.request(call, new ResponseHandler<List<HomePage>>() {
            @Override
            public void onSuccess(List<HomePage> homePages) {
                iPagePresenter.BackHomePage(homePages);
            }

            @Override
            public void onFailure(MyError e) {
                iPagePresenter.BackFaile(e);
            }
        });
    }

    public void getWorkRecords(String cfgID){
        String opType="WorkRecords";
        HttpClient httpClient=new HttpClient(context);
        Call<List<WorkRecords>> call =httpClient.service(API.XYService).workRecords(opType,cfgID);
        httpClient.request(call, new ResponseHandler<List<WorkRecords>>() {
            @Override
            public void onSuccess(List<WorkRecords> workRecords) {
                iPagePresenter.BackWorkRecord(workRecords);
            }

            @Override
            public void onFailure(MyError e) {
                iPagePresenter.BackFaile(e);
            }
        });
    }

    public void getWorkStatisticsByDay(String cfgID){
        String opType="WorkStatisticsByDay";
        HttpClient httpClient=new HttpClient(context);
        Call<List<WorkStatisticsByDay>> call =httpClient.service(API.XYService).workStatisticsByDay(opType,cfgID);
        httpClient.request(call, new ResponseHandler<List<WorkStatisticsByDay>>() {
            @Override
            public void onSuccess(List<WorkStatisticsByDay> workStatisticsByDays) {
                iPagePresenter.BackworkStatisticsByDays(workStatisticsByDays);
            }

            @Override
            public void onFailure(MyError e) {
                iPagePresenter.BackFaile(e);
            }
        });
    }

    public void getWorkStatisticsByWeek(String cfgID){
        String opType="WorkStatisticsByWeek";
        HttpClient httpClient=new HttpClient(context);
        Call<List<WorkStatisticsByWeek>> call =httpClient.service(API.XYService).workStatisticsByWeek(opType,cfgID);
        httpClient.request(call, new ResponseHandler<List<WorkStatisticsByWeek>>() {
            @Override
            public void onSuccess(List<WorkStatisticsByWeek> workStatisticsByWeeks) {
                iPagePresenter.BackworkStatisticsByWeek(workStatisticsByWeeks);
            }

            @Override
            public void onFailure(MyError e) {
                iPagePresenter.BackFaile(e);
            }
        });
    }

    public void getWorkStatisticsByMonth(String cfgID){
        String opType="WorkStatisticsByMonth";

        HttpClient httpClient=new HttpClient(context);
        Call<List<WorkStatisticsByMonth>> call =httpClient.service(API.XYService).workStatisticsByMonth(opType,cfgID);
        httpClient.request(call, new ResponseHandler<List<WorkStatisticsByMonth>>() {
            @Override
            public void onSuccess(List<WorkStatisticsByMonth> workStatisticsByMonths) {
                iPagePresenter.BackworkStatisticsByMonth(workStatisticsByMonths);
            }

            @Override
            public void onFailure(MyError e) {
                iPagePresenter.BackFaile(e);
            }
        });
    }
}
