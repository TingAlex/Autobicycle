package com.comsoftstar.autobicycle.Model.NetWork.http;

import com.comsoftstar.autobicycle.Model.Bean.CallBack.Register.R_Result;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by SJ on 2018/6/20.
 */

public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {
    private Gson gson;
    private Type type;

    public MyGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }
    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.toString();
        try{
            R_Result baseBean = gson.fromJson(response,R_Result.class);
            throw baseBean;
            //return baseBean;
        }finally {
            value.close();
        }
    }
}
