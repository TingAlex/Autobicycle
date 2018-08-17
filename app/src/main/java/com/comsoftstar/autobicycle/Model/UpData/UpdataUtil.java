package com.comsoftstar.autobicycle.Model.UpData;

/**
 * Created by SJ on 2018/6/6.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by admin on 2018/3/8.
 */
//单例
public class UpdataUtil {
    private static volatile UpdataUtil mInstance;
    private UpData_CallBack upData_CallBack;
    private Context mContext;

    private UpdataUtil(Context context, UpData_CallBack upData_CallBack) {
        this.upData_CallBack = upData_CallBack;
        this.mContext = context.getApplicationContext();
    }

    public static UpdataUtil getInstance(Context context, UpData_CallBack upData_CallBack) {
        if (mInstance == null) {
            synchronized (UpdataUtil.class) {
                if (mInstance == null) {
                    mInstance = new UpdataUtil(context, upData_CallBack);
                }
            }
        }
        return mInstance;
    }

    public interface UpData_CallBack {
        void start();

        void complete(boolean b);

        void falie(String msg);

        void progress(long progress, long value);
    }

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case -1:
                    upData_CallBack.start();
                    break;
                case 0:
                    long progress = msg.getData().getLong("progress");
                    long len = msg.getData().getLong("len");
                    upData_CallBack.progress(progress, len);
                    break;
                case 1:
                    upData_CallBack.complete(true);
                    installation(mContext, msg.obj.toString());
                    break;
                case 2:
                    upData_CallBack.complete(false);
                    break;
                default:
                    break;
            }
        }
    };

    public void updatafile(final String url, final String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean sdCardExist = Environment.getExternalStorageState()
                        .equals(Environment.MEDIA_MOUNTED); //判断sd卡是否存在
                if (!sdCardExist) {
                    upData_CallBack.falie("未发现sd卡！");
                    return;
                }
                Message msg = myHandler.obtainMessage();
                msg.what = -1;
                myHandler.sendMessage(msg); //发送消息
                File filepath = new File(path);
                if (!filepath.getParentFile().exists()) {
                    filepath.getParentFile().mkdirs();
                }
                try {

                    //String urlStr=url.substring(0,url.lastIndexOf("/")+1)+URLEncoder.encode(url.substring(url.lastIndexOf("/")+1,url.length()),"GBK");
                    String urlStr = url;
                    URL url = new URL(urlStr);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5 * 1000);
                    conn.setRequestMethod("GET");
//                    conn.setRequestProperty("Connection", "Keep-Alive");
//                    conn.setRequestProperty("Content-Type", "*/*");
//                    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
                    // 读取网络流到inStream中
                    InputStream inStream = conn.getInputStream();
                    long totalLen = conn.getContentLength();
                    // 定入二进制到文件
                    if (filepath.exists()) {
                        filepath.delete();
                    }
                    // 在文件系统中根据路径创建一个新的空文件
                    filepath.createNewFile();
                    OutputStream outStream = new FileOutputStream(filepath);
                    byte[] buff = new byte[1024];
                    int hasRead = -1;
                    long count = 0;
                    //在子线程中


                    while ((hasRead = inStream.read(buff)) != -1) {
                        count += hasRead;
                        outStream.write(buff, 0, hasRead);
                        msg.what = 0; //消息标识
                        Bundle _bundle = new Bundle();
                        _bundle.putLong("progress", count);
                        _bundle.putLong("len", totalLen);
                        msg.setData(_bundle);
                        myHandler.sendMessage(msg); //发送消息
                    }

                    outStream.close();
                    inStream.close();
                    if (totalLen != count) {
                        msg.what = 2;
                        myHandler.sendMessage(msg); //发送消息
                    }
                    msg.what = 1;
                    msg.obj = filepath.getAbsolutePath();
                    myHandler.sendMessage(msg); //发送消息

                    //conn.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    /**
     * 安装
     *
     * @param mContext
     * @param apkFilePath
     */
    public static void installation(Context mContext, String apkFilePath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mContext, mContext.getPackageName() + ".fileprovider", new File(apkFilePath));
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(new File(apkFilePath)), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (mContext.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            mContext.startActivity(intent);
        }
    }

    //=======================数据模拟==============
    Handler mHandler = new Handler();
    Runnable r;
    int n = 0;

    public void moni() {
        upData_CallBack.start();
        r = new Runnable() {

            @Override
            public void run() {
//do something
//每隔1s循环执行run方法
// 内容
                n += 10;
                upData_CallBack.progress(n, 100);
                if (n >= 100) {
                    mHandler.removeCallbacks(r);//结束计时器
                    upData_CallBack.complete(true);
                    return;

                } else {
                    upData_CallBack.complete(false);
                }
                mHandler.postDelayed(this, 500);
            }
        };
        mHandler.postDelayed(r, 100);//1秒后启动计时器
        //
    }
}
