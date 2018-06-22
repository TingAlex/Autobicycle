package com.comsoftstar.autobicycle.Model.Bean.CallBack.Register;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by SJ on 2018/6/20.
 */

public class R_Result extends IOException{


    /**
     * result : 验证码已发送
     */

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
