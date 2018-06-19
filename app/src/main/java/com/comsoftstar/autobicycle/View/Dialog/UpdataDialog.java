package com.comsoftstar.autobicycle.View.Dialog;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;

/**
 * Created by admin on 2018/3/1.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;

import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.DrawableRes;

import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.comsoftstar.autobicycle.Model.UpData.UpdataUtil;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.Util.Logs;

import static android.content.ContentValues.TAG;


/**
 * Created by admin on 2018/3/8.
 */

public class UpdataDialog extends Dialog implements View.OnClickListener {
    private Context mContext;
    private boolean isOpenNotify=false,isprogressDialog=false;
    private IntentFilter intentFilter;
    // private MyBroadcastReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;
    private NotificationManager mNotificationManager=null;
    private Notification.Builder mBuilder=null;
    private UpdataDialogCallBack updataDialogCallBack;
    private String version,updataContent;//filepath
    private TextView mTextView0,mTextView1;
    public interface UpdataDialogCallBack{
        void enter(NotificationManager mNotificationManager,Notification.Builder mBuilder,ProgressDialog progressDialog);
    }
    public UpdataDialog(@NonNull Context context,UpdataDialogCallBack updataDialogCallBack) {
        super(context);
        //this.filepath=filepath;
        this.mContext=context;
        this.updataDialogCallBack=updataDialogCallBack;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_updata);
        findViewById(R.id.tv_updata).setOnClickListener(this);
        findViewById(R.id.tv_cancelupdata).setOnClickListener(this);
        mTextView0=(TextView) findViewById(R.id.tv_version);
        mTextView1=(TextView) findViewById(R.id.tv_updatacontent);
        mTextView0.setText(version);
        mTextView1.setText(updataContent);
        setCancelable(false);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){

            case R.id.tv_updata:
                if (isOpenNotify) {
                    openNotify();
                }
                if (isprogressDialog){
                    progressDialog();
                }
                updataDialogCallBack.enter(isOpenNotify?mNotificationManager:null,isOpenNotify?mBuilder:null,isprogressDialog?progressDialog:null);
                break;
            case R.id.tv_cancelupdata:
                dismiss();
                break;
        }
    }
    public UpdataDialog setVersion(String version){
        Logs.d(TAG, "setVersion: 0" );
        this.version=version;

        return this;
    }

    /**
     * 设置更新内容
     * @param content
     * @return
     */
    public UpdataDialog setUpdataContent(String content){
        this.updataContent=content;
        return this;
    }
    //打开通知
    private void openNotify(){
//        localBroadcastManager = LocalBroadcastManager.getInstance(mContext);
//        intentFilter = new IntentFilter();
//        intentFilter.addAction("com.android.UPDATA");
//        localReceiver = new MyBroadcastReceiver();
//        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
        // 创建一个NotificationManager的引用
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        //判断是否是8.0上设备
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelID = "1";
            String channelName = "channel_name";
            NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder =new Notification.Builder(mContext.getApplicationContext())
                    .setSound(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setSmallIcon(res)                                        //设置通知的图标
                    .setTicker("更新提醒")                                                      //设置状态栏的标题
                    .setContentTitle("下载进度")                                                //设置标题
                    .setContentText("0%")                                                       //消息内容
                    .setProgress(100, 0, false)
                    .setDefaults(Notification.DEFAULT_ALL)                                      //设置默认的提示音
                    .setOngoing(false)                                                           //让通知左右滑的时候不能取消通知
                    .setAutoCancel(true);                                                        //打开程序后图标消失
            //创建通知时指定channelID

            mBuilder.setChannelId(channelID);
                                                     //打开程序后图标消失
            mNotificationManager.notify(1001,mBuilder.build());
        }else {
            mBuilder = new Notification.Builder(mContext.getApplicationContext())
                    .setSound(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setSmallIcon(res)                                        //设置通知的图标
                    .setTicker("更新提醒")                                                      //设置状态栏的标题
                    .setContentTitle("下载进度")                                                //设置标题
                    .setContentText("0%")                                                       //消息内容
                    .setProgress(100, 0, false)
                    .setDefaults(Notification.DEFAULT_ALL)                                      //设置默认的提示音
                    .setOngoing(false)                                                           //让通知左右滑的时候不能取消通知
                    .setAutoCancel(true);                                                        //打开程序后图标消失
            mNotificationManager.notify(1001, mBuilder.build());
        }
//发送
    }
    private int res;

    /**
     * 是否开启通知进度
     * @param b
     * @return
     */
    public UpdataDialog isopenNotify(boolean b){
        isOpenNotify=b;
        return this;
    }
    public UpdataDialog setImg(@DrawableRes int res){
        this.res=res;
        return this;
    }

    public UpdataDialog isprogressDialog(boolean b){
        isprogressDialog=b;
        return this;
    }
    ProgressDialog progressDialog=null;

    /**
     * 请求安装
     */
    public void progressDialog(){
        progressDialog=new ProgressDialog(mContext);
        progressDialog.setIcon(res);
        progressDialog.setTitle("正在下载文件。。。");
        progressDialog.setMessage("请稍后。。");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//设置进度条对话框//样式（水平，旋转）
        //进度最大值
        progressDialog.setMax(100);
        //TODO:下载对话框两个按钮拿掉
//        progressDialog.setButton("安装",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//               // UpdataUtil.installation(getContext(),filepath);
//                dismiss();
//                progressDialog.dismiss();
//            }
//        });
//        progressDialog.setButton2("取消",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dismiss();
//                progressDialog.dismiss();
//            }
//        });
        //显示
        progressDialog.show();


    }
//    class MyBroadcastReceiver extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context, "11", Toast.LENGTH_SHORT).show();
//        }
//    }
}