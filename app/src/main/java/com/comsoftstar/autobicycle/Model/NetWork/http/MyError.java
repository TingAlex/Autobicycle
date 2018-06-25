package com.comsoftstar.autobicycle.Model.NetWork.http;

import java.io.IOException;

/**
 * Created by apple on 16/8/20.
 */
public class MyError extends IOException{

    /**
     * code : un_caught_error
     * message : 未捕捉的错误
     * data : []
     * param :
     */

    private int code;
    private String message;
    private String param;
    private Object data;

    public int getCode() {
        return code;
    }
    public MyError()
    {
        code =400;
        message ="未知异常";
    }
    public MyError(String message){
        code =400;
        this.message=message;
    }

    public MyError setCode(int code) {

        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MyError setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getParam() {
        return param;
    }

    public MyError setParam(String param) {
        this.param = param;
        return this;
    }

    public Object getData() {
        return data;
    }

    public MyError setData(Object data) {
        this.data = data;
        return this;
    }
}
