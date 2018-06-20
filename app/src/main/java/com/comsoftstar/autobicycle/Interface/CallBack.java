package com.comsoftstar.autobicycle.Interface;

/**
 * Created by SJ on 2018/6/20.
 */

public interface CallBack<T> {
    void success(T result);
    void faile(String s);
}
