package com.comsoftstar.autobicycle.Model.Bean;

/**
 * Author       : SJ
 * Time         : 2018/8/4  11:59
 * Description  : WorkStatisticsByDay
 */

public class WorkStatisticsByDay {

    /**
     * WorkDate : 08-01
     * WorkMile : 738.508
     */

    private String WorkDate;
    private double WorkMile;

    public String getWorkDate() {
        return WorkDate;
    }

    public void setWorkDate(String WorkDate) {
        this.WorkDate = WorkDate;
    }

    public double getWorkMile() {
        return WorkMile;
    }

    public void setWorkMile(double WorkMile) {
        this.WorkMile = WorkMile;
    }
}
