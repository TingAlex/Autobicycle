package com.comsoftstar.autobicycle.Model.NetWork.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.comsoftstar.autobicycle.Model.Bean.CallBack.R_Result;
import com.comsoftstar.autobicycle.Model.NetWork.NetGet.HttpService;
import com.comsoftstar.autobicycle.Util.HttpLogger;
import com.comsoftstar.autobicycle.Util.Logs;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by apple on 16/8/20.
 */
public class HttpClient<T> {

    private static Context mContext;
    public final int SET_TIME = 60;
    private String tag = "HttpClient";

    public HttpClient(Context context) {
        mContext = context;
    }

    /**
     * 请求数据
     *
     * @param responseHandler 接口回调
     */
    public void request(Call<T> call, final ResponseHandler responseHandler) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    responseHandler.onSuccess(response.body());
                } else {
                    MyError error = null;
                    String errorString = null;
                    try {
                        errorString = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (errorString != null) {
                        try {
                            error = new Gson().fromJson(errorString, MyError.class);
                        } catch (JsonSyntaxException e) {
                            Logs.d(tag, "出现异常，无法将errorString转换成Error对象");
                            responseHandler.onFailure(new MyError(errorString));
                        }
                    }
                    if (error != null) {
                        responseHandler.onFailure(error.setCode(response.code()));
                    } else {
                        responseHandler.onFailure(new MyError().setCode(response.code()));
                    }
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (t instanceof R_Result) {
                    MyError error = new MyError().setCode(0).setMessage(((R_Result) t).getResult());
                    responseHandler.onFailure(error);
                } else {

                    responseHandler.onFailure(new MyError().setMessage(t.getCause().toString()));
                }

            }
        });
    }

    public HttpService service(String baseUrl) {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client;
        client = new OkHttpClient.Builder()
                // .addInterceptor(REWRITE_REQUEST_INTERCEPTOR)   //添加拦截器
                .addInterceptor(logInterceptor)
                .connectTimeout(SET_TIME, TimeUnit.SECONDS)    //连接超时设置
                .writeTimeout(SET_TIME, TimeUnit.SECONDS)      //读写超时设置
                .readTimeout(SET_TIME, TimeUnit.SECONDS)
                .build();

        Retrofit sRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(MyGsonConverterFactory.create())  //json自动转换器
                // .client(client)
                .build();
        return sRetrofit.create(HttpService.class);
    }


    /**
     * 利用拦截器更改Request请求
     */
    private Interceptor REWRITE_REQUEST_INTERCEPTOR = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {

            Request original = chain.request();
            Request request;
            Request.Builder builder = original.newBuilder();
            try {
                //  builder.addHeader("User-Token", PreferencesUtil.getUser(mContext).getToken());
                // Log.d(Constans.LOG, "Token:  " + PreferencesUtil.getUser(mContext).getToken());
            } catch (Exception e) {
                e.printStackTrace();
            }
            request = builder
                    .addHeader("Device", "android")
                    .addHeader("Version", HttpClient.getAppVersionName(mContext))
                    .build();
            okhttp3.Response response = chain.proceed(request);

            return response;
        }
    };

    /**
     * 获得app版本名字
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        String versionName = null;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            versionName = "0.0.0";
        }
        return versionName;
    }


}
