package com.comsoftstar.autobicycle.View.Interface.IPresenter;

import com.comsoftstar.autobicycle.Model.Bean.HomePage;
import com.comsoftstar.autobicycle.Model.Bean.WorkRecords;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByDay;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByMonth;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByWeek;
import com.comsoftstar.autobicycle.Model.NetWork.http.MyError;

import java.util.List;

/**
 * Author       : SJ
 * Time         : 2018/8/4  12:45
 * Description  : IPagePresenter
 */

public interface IPagePresenter {
    void BackHomePage(List<HomePage> homePages);
    void BackWorkRecord(List<WorkRecords> workRecords);
    void BackworkStatisticsByDays(List<WorkStatisticsByDay> workStatisticsByDays);
    void BackworkStatisticsByWeek(List<WorkStatisticsByWeek> workStatisticsByWeeks);
    void BackworkStatisticsByMonth(List<WorkStatisticsByMonth> workStatisticsByMonths);
    void BackFaile(MyError error);
}
