package com.comsoftstar.autobicycle.View.Presenter;

import com.comsoftstar.autobicycle.App.Value;
import com.comsoftstar.autobicycle.Interface.CallBack;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Main.RepairRecordBean;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.R_Result;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Register.SalePoint;
import com.comsoftstar.autobicycle.View.Module.RepairModel;

import java.util.List;
import java.util.Map;

/**
 * Created by SJ on 2018/6/25.
 */

public class RepairPresenter {
    private RepairModel repairModel = new RepairModel();
    private IRepairView iRepairView;

    public interface IRepairView {
        void getSalePoints(List<SalePoint> salePoints);

        void getRepairRecord(List<RepairRecordBean> repairRecordBeans);

        void saveRepair(R_Result result);

        void faile(String s);
    }

    public RepairPresenter(IRepairView iRepairView) {
        this.iRepairView = iRepairView;
    }

    /**
     * 获取营业网点
     */
    public void getSalePoints() {
        repairModel.SalePoint(new CallBack<List<SalePoint>>() {
            @Override
            public void success(List<SalePoint> result) {
                Value.SalePoint = result;
                iRepairView.getSalePoints(result);
            }

            @Override
            public void faile(String s) {
                iRepairView.faile(s);
            }
        });
    }

    /**
     * 获取在线报修记录
     */
    public void getRepairRecord(String loginName) {
        repairModel.repairRecord(loginName, new CallBack<List<RepairRecordBean>>() {
            @Override
            public void success(List<RepairRecordBean> result) {
                iRepairView.getRepairRecord(result);
            }

            @Override
            public void faile(String s) {
                iRepairView.faile(s);
            }
        });
    }

    /**
     * 保存在线报修
     *
     * @param params
     */
    public void saveRepair(Map<String, String> params) {
        repairModel.saveRecord(params, new CallBack<R_Result>() {
            @Override
            public void success(R_Result result) {
                iRepairView.saveRepair(result);
            }

            @Override
            public void faile(String s) {
                iRepairView.faile(s);
            }
        });
    }
}
