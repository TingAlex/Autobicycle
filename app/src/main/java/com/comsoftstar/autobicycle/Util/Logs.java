package com.comsoftstar.autobicycle.Util;

/**
 * Created by SJ on 2018/6/11.
 */

import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * 日志打印工具类
 *
 * @author chengliang0315
 * @see <a>http://blog.csdn.net/chengliang0315</a>
 */
public class Logs {
    /**
     * 是否开启debug
     * 注意：使用Eclipse打包的时候记得取消Build Automatically，否则一直是true
     */
    public static void init(final boolean b) {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  //（可选）是否显示线程信息。默认值true
                .methodCount(4)         //（可选）要显示多少个方法行。默认2
                //.methodOffset(5)        //（可选）将内部方法调用隐藏到偏移量。默认 5

                //.logStrategy(LogCat) //（可选）更改日志策略以打印输出。默认LogCat
                .tag("SJIE_DEBUG")   //（可选）每个日志的全局标记。默认PRETTY_LOGGER .build
                .build();
        Logger.addLogAdapter(new DiskLogAdapter(formatStrategy){
                 @Override public boolean isLoggable(int priority, String tag) {
                     //true将打印日志消息，false将忽略它。
                     return b;
                 }
        });
    }

    /**
     * 错误
     */
    public static void e(String tag,String msg){
            Logger.t(tag).e(msg+"");
    }
    public static void e(String msg){
        Logger.e(msg+"");
    }
    /**
     * 调试
     */
    public static void d(String tag,String msg){
            Logger.t(tag).d( msg+"");
    }
    public static void d(String msg){
        Logger.d( msg+"");
    }
    /**
     * 信息
     */
    public static void i(String tag,String msg){
            Logger.t(tag).i( msg+"");
    }
    public static void i(String msg){
        Logger.i( msg+"");
    }
    /**
     * 异常信息
     */
    public static void tcatch(String tag,Throwable e){
            Logger.t(tag).e(e,"异常----");
    }
    public static void tcatch(Throwable e){
        Logger.e(e,"异常----");
    }
    /**
     * json数据打印
     */
    public static void json(String tag,String jsondata){
            Logger.t(tag).json(jsondata);
    }
    public static void json(String jsondata){
        Logger.json(jsondata);
    }
    /**
     * xml数据打印
     */
    public static void xml(String tag,String xmldata){
            Logger.t(tag).xml(xmldata);
    }
    public static void xml(String xmldata){
        Logger.xml(xmldata);
    }




//    public static LogUtils getInstance() {
//        return SingletonHolder.INSTANCE;
//    }
//
//    private static class SingletonHolder {
//        private static final LogUtils INSTANCE=new LogUtils();
//    }
}
