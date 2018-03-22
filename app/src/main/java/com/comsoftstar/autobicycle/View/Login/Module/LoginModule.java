package com.comsoftstar.autobicycle.View.Login.Module;






/**
 * Created by Administrator on 2017/9/28.
 */

public class LoginModule {

    public LoginModule(){
    }
    public static interface Login{
        void result(String s);
    }
    public  void login(final Login login){
 //       RequestParams params = new RequestParams("http://blog.csdn.net/oaitan/article/details/50773920");
//        NetUtil.onnetget(params, new NetReturn() {
//            @Override
//            public void success(String str) {
//                login.result(str);
//            }
//
//            @Override
//            public void error(String message) {
//
//            }
//        });
        login.result("qwe");
    }
}
