package com.comsoftstar.autobicycle.View.Interface.Iview;

import com.comsoftstar.autobicycle.Model.Bean.WorkRecords;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByDay;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByMonth;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByWeek;

import java.util.List;

/**
 * Author       : SJ
 * Time         : 2018/8/4  13:47
 * Description  : IDrivingHistory
 */

public interface IWorkBy extends IBaseView {
    void setWorkDays(List<WorkStatisticsByDay> workStatisticsByDays);
    void setWorkWeeks(List<WorkStatisticsByWeek> workStatisticsByWeeks);
    void setWorkMonths(List<WorkStatisticsByMonth> workStatisticsByMonths);
}
