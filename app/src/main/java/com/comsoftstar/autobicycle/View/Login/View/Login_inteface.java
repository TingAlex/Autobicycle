package com.comsoftstar.autobicycle.View.Login.View;

import com.comsoftstar.autobicycle.Model.NetWork.http.MyError;

/**
 * Created by Administrator on 2017/9/28.
 */

public interface Login_inteface {
    void loginsuccess();
    void autoaccount(String account,String password,boolean ischeck);
    void loginfaile(MyError result);
}
