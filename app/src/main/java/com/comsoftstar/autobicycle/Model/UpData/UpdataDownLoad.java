package com.comsoftstar.autobicycle.Model.UpData;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.Toast;

import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.View.Dialog.UpdataDialog;

/**
 * Created by SJ on 2018/6/6.
 */

public class UpdataDownLoad {
    private static UpdataDownLoad mInstance;

    public  Context mContext;
    private UpdataDownLoad(Context mContext) {
        this.mContext=mContext;
    }

    public static synchronized UpdataDownLoad getInstance(Context mContext) {
        if (mInstance == null) {
            mInstance = new UpdataDownLoad(mContext);
        }
        return mInstance;
    }

    public void dowmload(final String fileurl,final String filepath){
        new UpdataDialog(mContext, new UpdataDialog.UpdataDialogCallBack() {
            @Override
            public void enter(final NotificationManager mNotificationManager, final Notification.Builder mBuilder, final ProgressDialog progressDialog) {
                //final Button positiveButton = progressDialog.getButton(ProgressDialog.BUTTON_POSITIVE);
                UpdataUtil.getInstance(mContext, new UpdataUtil.UpData_CallBack() {
                    @Override
                    public void start() {

                        //positiveButton.setEnabled(false);
                    }
                    @Override
                    public void complete(boolean b) {
                        //结束广播
                        if (b) {
                            Toast.makeText(mContext, "下载完成", Toast.LENGTH_SHORT).show();
                            if (mNotificationManager != null) {
                                mNotificationManager.cancel(1001);
                            }
                           // positiveButton.setEnabled(true);
                        }
                    }
                    @Override
                    public void falie(String msg) {
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void progress(final long progress, long max) {
                        int len = (int) (((double) progress / (double) max) * 100.0f);
                        if (mBuilder != null) {
                            mBuilder.setProgress(100, len, false);
                            String str = String.valueOf(len) + "%";
                            mBuilder.setContentText(str);
                            mNotificationManager.notify(1001, mBuilder.build());
                        }
                        if (progressDialog != null) {
                            progressDialog.setProgress(len);
                        }
                    }
                }).moni();//.updatafile(fileurl,filepath);
            }
    }).isprogressDialog(true).isopenNotify(true)//通知
        .setImg(R.mipmap.ic_launcher_round)
        .setVersion("V1.1.1")
        .setUpdataContent("").show();
    }

}
