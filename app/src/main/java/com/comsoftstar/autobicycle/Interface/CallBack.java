package com.comsoftstar.autobicycle.Interface;

import com.comsoftstar.autobicycle.Model.NetWork.http.MyError;

/**
 * Created by SJ on 2018/6/20.
 */

public interface CallBack<T> {
    void success(T result);

    void faile(String s);
}
