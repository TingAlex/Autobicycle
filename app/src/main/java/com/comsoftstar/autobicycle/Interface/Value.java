package com.comsoftstar.autobicycle.Interface;

/**
 * Created by Administrator on 2017/9/28.
 */

public class Value {
    //登录配置文件名
    public static final String PATH="DDLOGIN";

    //全局用户名
    public static String UserName="";
    //登录
    public static enum Login{
        PASSWORD("密码登录"),VERCODE("验证码登录");

        Login(String value){
            this.value=value;
        }
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    //验证码
    public static enum VerCode{
        LOGIN("登陆"),REGISTER("注册");
        VerCode(String value){
            this.value=value;
        }

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


}
