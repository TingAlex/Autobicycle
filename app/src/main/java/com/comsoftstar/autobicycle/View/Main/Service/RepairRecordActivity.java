package com.comsoftstar.autobicycle.View.Main.Service;

import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.comsoftstar.autobicycle.Adapter.RepairRecordAdapter;
import com.comsoftstar.autobicycle.App.Value;
import com.comsoftstar.autobicycle.Base.BaseActivity;
import com.comsoftstar.autobicycle.Control.MyToolBar;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Main.RepairRecordBean;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.R_Result;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Register.SalePoint;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.View.Presenter.RepairPresenter;
import com.comsoftstar.autobicycle.databinding.ActivityRepairrecordBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepairRecordActivity extends BaseActivity<ActivityRepairrecordBinding> implements RepairPresenter.IRepairView, View.OnClickListener {
    private ActivityRepairrecordBinding binding;
    private RepairPresenter repairPresenter;
    private RepairRecordAdapter adapter;
    private String salePoint;
    private String salePointValue;
    private boolean isslect = false;

    @Override
    public int setLayoutId() {
        return R.layout.activity_repairrecord;
    }

    @Override
    public void initView(ActivityRepairrecordBinding binding) {
        this.binding = binding;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyRepair.setLayoutManager(layoutManager);
        Toolbar toolbar = MyToolBar.newInstance()
                .init(new Toolbar(this))
                .addTitle(getString(R.string.Setting))
                .addLogo()
                .addHome(1, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        addToolBar(binding.layoutRepair, toolbar);
        binding.setOnclicklisten(this);
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        adapter = new RepairRecordAdapter(getApplicationContext(), new ArrayList<RepairRecordBean>());
        binding.recyRepair.setAdapter(adapter);
        repairPresenter = new RepairPresenter(this);
        repairPresenter.getSalePoints();
        repairPresenter.getRepairRecord(Value.UserName);
        binding.spinnerRepair.setOnItemSelectedListener(itemselect);

    }

    //region RepairPresenter.IRepairView接口实现

    /**
     * 营业网点信息
     *
     * @param salePoints
     */
    @Override
    public void getSalePoints(List<SalePoint> salePoints) {
        Value.SalePoint = salePoints;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, objTranList(salePoints));
        binding.spinnerRepair.setAdapter(adapter);
    }

    @Override
    public void getRepairRecord(List<RepairRecordBean> repairRecordBeans) {
        adapter.setdata(repairRecordBeans);
    }

    @Override
    public void saveRepair(R_Result result) {
        toast(result.getResult());
    }

    @Override
    public void faile(String s) {

    }
    //endregion

    /**
     * 营业网点转化为spinner列表集合
     *
     * @param salePoints
     * @return
     */
    private List<String> objTranList(List<SalePoint> salePoints) {
        List<String> list = new ArrayList<>();
        list.add("选择网点");
        for (SalePoint item :
                salePoints) {
            list.add(item.getText());
        }
        return list;
    }

    //spinner 监听
    private AdapterView.OnItemSelectedListener itemselect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position != 0) {
                isslect = true;
                salePoint = binding.spinnerRepair.getSelectedItem().toString();
                salePointValue = Value.SalePoint.get(position - 1).getValue();
            } else {
                isslect = false;
            }
            //Toast.makeText(RepairRecordActivity.this, ""+position, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_repairsubmit:
                if (TextUtils.isEmpty(binding.editRepair.getText())) {
                    Toast.makeText(this, "请输入信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isslect) {
                    Toast.makeText(RepairRecordActivity.this, "请选择营业网点！", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String, String> params = new HashMap<>();
                params.put("loginName", Value.UserName);
                params.put("Guid", "");
                params.put("UserProblem", binding.editRepair.getText().toString());
                params.put("PointNo", salePointValue);
                params.put("PointName", salePoint);
                repairPresenter.saveRepair(params);
                break;
        }
    }
}
