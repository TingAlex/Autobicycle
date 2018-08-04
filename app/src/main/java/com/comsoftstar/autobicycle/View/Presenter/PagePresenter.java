package com.comsoftstar.autobicycle.View.Presenter;

import android.content.Context;

import com.comsoftstar.autobicycle.Model.Bean.HomePage;
import com.comsoftstar.autobicycle.Model.Bean.WorkRecords;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByDay;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByMonth;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByWeek;
import com.comsoftstar.autobicycle.Model.NetWork.http.MyError;
import com.comsoftstar.autobicycle.View.Interface.IPresenter.IPagePresenter;
import com.comsoftstar.autobicycle.View.Interface.Iview.IBaseView;
import com.comsoftstar.autobicycle.View.Interface.Iview.IDrivingHistory;
import com.comsoftstar.autobicycle.View.Interface.Iview.IFragmentView;
import com.comsoftstar.autobicycle.View.Interface.Iview.IWorkBy;
import com.comsoftstar.autobicycle.View.Module.PageModal;

import java.util.List;
import java.util.Map;

/**
 * Author       : SJ
 * Time         : 2018/8/4  12:31
 * Description  : PagePresenter
 */

public class PagePresenter<T extends IBaseView> implements IPagePresenter{
    private Context context;
    private PageModal pageModal;
    private T iView;
    public PagePresenter(Context context, T iView){
        this.context=context;
        pageModal=new PageModal(context,this);
        this.iView=iView;
    }
    public void getHomePage(Map maps){
        pageModal.getHomePage(maps);
    }
    public void getWorkRecords(String cfgID){
        pageModal.getWorkRecords(cfgID);
    }

    public void getWorkStatisticsBy(int n,String cfgID){
        switch (n){
            case 0:
                pageModal.getWorkStatisticsByDay(cfgID);
                break;
            case 1:
                pageModal.getWorkStatisticsByWeek(cfgID);
                break;
            case 2:
                pageModal.getWorkStatisticsByMonth(cfgID);
                break;
        }

    }

    @Override
    public void BackHomePage(List<HomePage> homePages) {
        ((IFragmentView)iView).setHomePage(homePages.get(0));
    }

    @Override
    public void BackWorkRecord(List<WorkRecords> workRecords) {
        ((IDrivingHistory)iView).setWorkRecords(workRecords);
    }

    @Override
    public void BackworkStatisticsByDays(List<WorkStatisticsByDay> workStatisticsByDays) {
        ((IWorkBy)iView).setWorkDays(workStatisticsByDays);
    }

    @Override
    public void BackworkStatisticsByWeek(List<WorkStatisticsByWeek> workStatisticsByWeeks) {
        ((IWorkBy)iView).setWorkWeeks(workStatisticsByWeeks);
    }

    @Override
    public void BackworkStatisticsByMonth(List<WorkStatisticsByMonth> workStatisticsByMonths) {
        ((IWorkBy)iView).setWorkMonths(workStatisticsByMonths);
    }

    @Override
    public void BackFaile(MyError error) {
        iView.faileMsg(error.getMessage());
    }
}
