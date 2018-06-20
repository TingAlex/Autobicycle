package com.comsoftstar.autobicycle.Model.NetWork.http;

import com.comsoftstar.autobicycle.Model.Bean.CallBack.Register.R_Result;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by SJ on 2018/6/20.
 */

public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    MyGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override public T convert(ResponseBody value) throws IOException {
        //注意：ResponseBody只能读取一次
        final String str=value.string();
        JsonReader jsonReader = gson.newJsonReader(new StringReader(str));
        try {
           T result= adapter.read(jsonReader);
            return result;
        }catch (Exception e){
            try {
                Gson gson = new Gson();
                R_Result person = gson.fromJson(str, R_Result.class);
                throw person;
            }catch (Exception e2){
                throw e2;
            }

        }
        finally {
            value.close();
        }
    }
//    @Override public T convert(ResponseBody value) throws IOException {
//        JsonReader jsonReader = gson.newJsonReader(value.charStream());
//        try {
//            return adapter.read(jsonReader);
//        } finally {
//            value.close();
//        }
//    }
}
