package com.comsoftstar.autobicycle.View.Interface.Iview;

import com.comsoftstar.autobicycle.Model.Bean.WorkRecords;

import java.util.List;

/**
 * Author       : SJ
 * Time         : 2018/8/4  13:47
 * Description  : IDrivingHistory
 */

public interface IDrivingHistory extends IBaseView {
    void setWorkRecords(List<WorkRecords> workRecords);
}
