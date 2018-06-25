package com.comsoftstar.autobicycle.Model.NetWork.NetGet;



import com.comsoftstar.autobicycle.Interface.CallBack;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Register.SalePoint;


import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;



/**
 * Created by SJ on 2018/6/25.
 */
public class NetUtilTest  {
    @Test
    public void salePoint() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        NetUtil.getInstance().SalePoint(new CallBack<List<SalePoint>>() {
            @Override
            public void success(List<SalePoint> result) {
                //Log.i("---test2", "success");
                signal.countDown();
            }

            @Override
            public void faile(String s) {
                //Log.i("---test2", s);
                signal.countDown();
            }
        });
//等待响应
        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}