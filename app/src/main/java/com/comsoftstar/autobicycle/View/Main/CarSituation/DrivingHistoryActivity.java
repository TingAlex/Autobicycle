package com.comsoftstar.autobicycle.View.Main.CarSituation;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.comsoftstar.autobicycle.Base.BaseActivity;
import com.comsoftstar.autobicycle.Business.StaticData;
import com.comsoftstar.autobicycle.Interface.RecyclerItemClickListener;
import com.comsoftstar.autobicycle.Model.Bean.WorkRecords;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.Adapter.HistoryAdapter;
import com.comsoftstar.autobicycle.Model.Bean.Historyitem;
import com.comsoftstar.autobicycle.View.Interface.Iview.IDrivingHistory;
import com.comsoftstar.autobicycle.View.Interface.Iview.IFragmentView;
import com.comsoftstar.autobicycle.View.Presenter.PagePresenter;
import com.comsoftstar.autobicycle.databinding.ActivityDrivinghistoryBinding;

import java.util.ArrayList;
import java.util.List;

public class DrivingHistoryActivity extends BaseActivity<ActivityDrivinghistoryBinding> implements IDrivingHistory{
    private Historyitem historyitem;
    private HistoryAdapter historyAdapter;
    private ArrayList<Historyitem> listt=new ArrayList<>();
    private RecyclerView recyclerView;
    private PagePresenter<IDrivingHistory> pagePresenter;

    @Override
    public int setLayoutId() {
        return R.layout.activity_drivinghistory;
    }

    @Override
    public void initView(ActivityDrivinghistoryBinding binding) {
        final Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.DrivingHistory);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);
            actionBar.setHomeButtonEnabled(true);
        }
        initUI();
        pagePresenter=new PagePresenter<IDrivingHistory>(getApplicationContext(),this);
        pagePresenter.getWorkRecords(StaticData.loginResults.get(0).getCfgID());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI(){
        listt.clear();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int posotion) {

            }
        }));

    }

    @Override
    public void faileMsg(String msg) {
        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWorkRecords(List<WorkRecords> workRecords) {
        listt.clear();
        for (int i = 0; i < workRecords.size(); i++) {
            String[] arr=workRecords.get(i).getWorkDate().split(" ");
            String date=arr[0];
            String time=arr[1];
            historyitem=new Historyitem(date,time,workRecords.get(i).getStartAddress(),workRecords.get(i).getEndAddress(),String.valueOf(workRecords.get(i).getWorkMiles()));
            listt.add(historyitem);
        }

        historyAdapter=new HistoryAdapter(listt);
        recyclerView.setAdapter(historyAdapter);

    }
}
