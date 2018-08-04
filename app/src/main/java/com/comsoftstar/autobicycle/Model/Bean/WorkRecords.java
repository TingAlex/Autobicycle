package com.comsoftstar.autobicycle.Model.Bean;

/**
 * Author       : SJ
 * Time         : 2018/8/4  11:54
 * Description  : WorkRecords
 */

public class WorkRecords {


    /**
     * StartAddress : 江苏省无锡市滨湖区
     * EndAddress : 江苏省无锡市滨湖区
     * WorkMiles : 2.226
     * WorkDate : 2018-08-01 19:22
     */

    private String StartAddress;
    private String EndAddress;
    private double WorkMiles;
    private String WorkDate;

    public String getStartAddress() {
        return StartAddress;
    }

    public void setStartAddress(String StartAddress) {
        this.StartAddress = StartAddress;
    }

    public String getEndAddress() {
        return EndAddress;
    }

    public void setEndAddress(String EndAddress) {
        this.EndAddress = EndAddress;
    }

    public double getWorkMiles() {
        return WorkMiles;
    }

    public void setWorkMiles(double WorkMiles) {
        this.WorkMiles = WorkMiles;
    }

    public String getWorkDate() {
        return WorkDate;
    }

    public void setWorkDate(String WorkDate) {
        this.WorkDate = WorkDate;
    }
}
