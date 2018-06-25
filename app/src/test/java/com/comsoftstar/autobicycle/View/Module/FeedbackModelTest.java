package com.comsoftstar.autobicycle.View.Module;

import com.comsoftstar.autobicycle.Interface.CallBack;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Main.FeedBackBean;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.R_Result;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by SJ on 2018/6/25.
 */

public class FeedbackModelTest{

    @Test
    public void feedbackRecord() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        FeedbackModel feedbackModel=new FeedbackModel();
        feedbackModel.feedbackRecord("18068261236", new CallBack<List<FeedBackBean>>() {
            @Override
            public void success(List<FeedBackBean> result) {
                signal.countDown();
            }

            @Override
            public void faile(String s) {
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


    @Test
    public void saveFeedback() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        FeedbackModel feedbackModel=new FeedbackModel();
        Map<String,String> params=new HashMap<>();
        params.put("loginName","18068261236");
        params.put("Guid","");
        params.put("Suggestion","123");


        feedbackModel.saveFeedback(params, new CallBack<R_Result>() {
            @Override
            public void success(R_Result result) {
                signal.countDown();
            }

            @Override
            public void faile(String s) {
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