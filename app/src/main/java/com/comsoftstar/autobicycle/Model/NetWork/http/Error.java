package com.comsoftstar.autobicycle.Model.NetWork.http;

/**
 * Created by apple on 16/8/20.
 */
public class Error {

    /**
     * code : un_caught_error
     * message : 未捕捉的错误
     * data : []
     * param :
     */

    private String code;
    private String message;
    private String param;
    private Object data;

    public String getCode() {
        if(code==null)
            return "";
        return code;
    }
    public Error()
    {
        code = "404";
        message ="网络异常";
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
