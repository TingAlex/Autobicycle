package com.comsoftstar.autobicycle.Main.Model;

/**
 * Created by Administrator on 2017/10/13.
 */

public class Config {
    private  String CKTITLE;
    private  String SERVICETITLE;
    private  String MYTITLE;

    public Config(String CKTITLE, String SERVICETITLE, String MYTITLE) {
        this.CKTITLE = CKTITLE;
        this.SERVICETITLE = SERVICETITLE;
        this.MYTITLE = MYTITLE;
    }

    public String getCKTITLE() {
        return CKTITLE;
    }

    public String getSERVICETITLE() {
        return SERVICETITLE;
    }

    public String getMYTITLE() {
        return MYTITLE;
    }

    public void setCKTITLE(String CKTITLE) {
        this.CKTITLE = CKTITLE;
    }


    public void setSERVICETITLE(String SERVICETITLE) {
        this.SERVICETITLE = SERVICETITLE;
    }


    public void setMYTITLE(String MYTITLE) {
        this.MYTITLE = MYTITLE;
    }
}
