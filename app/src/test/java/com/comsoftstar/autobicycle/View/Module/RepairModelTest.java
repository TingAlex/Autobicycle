package com.comsoftstar.autobicycle.View.Module;

import com.comsoftstar.autobicycle.Interface.CallBack;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Main.RepairRecordBean;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.R_Result;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Register.SalePoint;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * Created by SJ on 2018/6/25.
 */
public class RepairModelTest {


    @Test
    public void repairRecord() throws Exception {
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        RepairModel repairModel=new RepairModel();
        repairModel.repairRecord("18068261236", new CallBack<List<RepairRecordBean>>() {
            @Override
            public void success(List<RepairRecordBean> result) {
                countDownLatch.countDown();
            }

            @Override
            public void faile(String s) {
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        }catch (Exception e){

        }
    }

    @Test
    public void saveRecord() throws Exception {
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        RepairModel repairModel=new RepairModel();
        Map<String,String> params=new HashMap<>();
        params.put("loginName","18068261236");
        params.put("Guid","");
        params.put("UserProblem","180");
        params.put("PointNo","YYD00000004");
        params.put("PointName","测试");
        repairModel.saveRecord(params, new CallBack<R_Result>() {
            @Override
            public void success(R_Result result) {
                countDownLatch.countDown();
            }

            @Override
            public void faile(String s) {
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        }catch (Exception e){

        }
    }

    /**
     * 获取营业网点
     * @throws Exception
     */
    @Test
    public void salePoint() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        RepairModel repairModel=new RepairModel();
        repairModel.SalePoint(new CallBack<List<SalePoint>>() {
            @Override
            public void success(List<SalePoint> result) {
                signal.countDown();
            }

            @Override
            public void faile(String s) {
                signal.countDown();
            }
        });
        try{
            signal.await();
        }catch (Exception e){

        }
    }

}