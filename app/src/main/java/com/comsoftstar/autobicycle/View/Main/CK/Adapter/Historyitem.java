package com.comsoftstar.autobicycle.View.Main.CK.Adapter;

/**
 * Created by Administrator on 2017/9/30.
 */

public class Historyitem {
    private String date,time,start,end;

    public Historyitem(String date, String time, String start, String end) {
        this.date = date;
        this.time = time;
        this.start = start;
        this.end = end;
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
