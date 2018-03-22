package com.comsoftstar.autobicycle.Model.Bean;

/**
 * Created by Administrator on 2017/9/30.
 */

public class Historyitem {
    private String date,time,start,end,tv_length;

    public String getTv_length() {
        return tv_length;
    }

    public void setTv_length(String tv_length) {
        this.tv_length = tv_length;
    }

    public Historyitem(String date, String time, String start, String end, String tv_length) {
        this.date = date;
        this.time = time;
        this.start = start;
        this.end = end;
        this.tv_length=tv_length;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
