package com.comsoftstar.autobicycle.Public.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/9/28.
 */

public class Tools {
    public static boolean checknetwork(Context context){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取所有网络连接的信息
        Network[] networks = connMgr.getAllNetworks();
        //用于存放网络连接信息
        StringBuilder sb = new StringBuilder();
        //通过循环将网络信息逐个取出来
        for (int i=0; i < networks.length; i++){
            //获取ConnectivityManager对象对应的NetworkInfo对象
            NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);
            sb.append(networkInfo.getTypeName() + " connect is " + networkInfo.isConnected());
            if ( networkInfo.isConnected()==false){
                Toast.makeText(context, "网络连接不可用", Toast.LENGTH_LONG).show();
                return false;
            }
        }
//networkInfo.getTypeName() 网络名称 WIFI或者MOBILE
        if (TextUtils.isEmpty(sb)){
            Toast.makeText(context, "网络未连接", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


}
