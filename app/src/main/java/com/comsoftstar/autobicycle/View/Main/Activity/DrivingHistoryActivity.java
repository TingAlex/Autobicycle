package com.comsoftstar.autobicycle.View.Main.Activity;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.comsoftstar.autobicycle.Base.BaseActivity;
import com.comsoftstar.autobicycle.Interface.RecyclerItemClickListener;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.Adapter.HistoryAdapter;
import com.comsoftstar.autobicycle.Model.Bean.Historyitem;
import com.comsoftstar.autobicycle.databinding.ActivityDrivinghistoryBinding;

import java.util.ArrayList;

public class DrivingHistoryActivity extends BaseActivity<ActivityDrivinghistoryBinding> {
    private Historyitem historyitem;
    private HistoryAdapter historyAdapter;
    private ArrayList<Historyitem> listt=new ArrayList<>();
    private RecyclerView recyclerView;

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
        for (int i = 0; i < 10; i++) {
            historyitem=new Historyitem("2017-09-30 星期六","17:08","江苏无锡宇野网络大厦B座1703","无锡惠山万达北广场","5km");
            listt.add(historyitem);
        }

        historyAdapter=new HistoryAdapter(listt);
        recyclerView.setAdapter(historyAdapter);

    }
}
