package com.comsoftstar.autobicycle.View.Presenter;

import com.comsoftstar.autobicycle.Interface.CallBack;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Main.FeedBack;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.R_Result;
import com.comsoftstar.autobicycle.View.Module.FeedbackModel;

import java.util.List;
import java.util.Map;

/**
 * Created by SJ on 2018/6/25.
 */

public class FeedBackPresenter {
    private FeedbackModel feedbackModel=new FeedbackModel();
    IFeedBackView iFeedBackView;
   public interface IFeedBackView{
       /**
        * 获取用户反馈
        * @param feedBacks
        */
        void feedback(List<FeedBack> feedBacks);

       /**
        * 提交反馈
        * @param result
        */
        void savefeed(R_Result result);

       /**
        * 结果信息
        * @param s
        */
        void faile(String s);
    }
    public FeedBackPresenter(IFeedBackView iFeedBackView){
        this.iFeedBackView=iFeedBackView;
    }

    /**
     * 获取用户反馈
     * @param loginName
     */
    public void getFeedBack( String loginName){
        feedbackModel.feedbackRecord(loginName, new CallBack<List<FeedBack>>() {
            @Override
            public void success(List<FeedBack> result) {
                iFeedBackView.feedback(result);
            }

            @Override
            public void faile(String s) {
                iFeedBackView.faile(s);
            }
        });
    }


    /**
     * 保存反馈
     * @param params
     */
    public void saveFeed(Map<String,String> params){
        feedbackModel.saveFeedback(params, new CallBack<R_Result>() {
            @Override
            public void success(R_Result result) {
                iFeedBackView.savefeed(result);
            }

            @Override
            public void faile(String s) {
                iFeedBackView.faile(s);
            }
        });
    }


}
