package com.comsoftstar.autobicycle.Model.NetWork.http;



/**
 * Created by apple on 16/8/20.
 */
public interface ResponseHandler<T> {

    //成功回调方法
     void onSuccess(T t);

    //失败回调方法
     void onFailure( MyError e);

}
