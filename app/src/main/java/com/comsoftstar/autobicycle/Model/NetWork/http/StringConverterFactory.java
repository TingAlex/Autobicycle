
package com.comsoftstar.autobicycle.Model.NetWork.http;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public final class StringConverterFactory extends Converter.Factory {

    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    final class StringResponseBodyConverter implements Converter<ResponseBody, String> {
        @Override
        public String convert(ResponseBody value) throws IOException {
            if (value.contentLength() == -1) {
                throw new MyError().setMessage("返回结果为null");
            }
            String s = new String(value.bytes());

            return s;
        }
    }

    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == String.class) {
            return new StringResponseBodyConverter();
        }
        return null;
    }

}
