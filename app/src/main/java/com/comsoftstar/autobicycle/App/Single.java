package com.comsoftstar.autobicycle.App;

import com.comsoftstar.autobicycle.Model.NetWork.http.HttpClient;

/**
 * Created by SJ on 2018/6/20.
 */

public class Single {
    public HttpClient httpClient = new HttpClient(App.context);

    private Single() {
    }

    public static Single getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final Single INSTANCE = new Single();
    }
}
