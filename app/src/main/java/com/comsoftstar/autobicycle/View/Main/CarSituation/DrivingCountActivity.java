package com.comsoftstar.autobicycle.View.Main.CarSituation;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.comsoftstar.autobicycle.Base.BaseActivity;
import com.comsoftstar.autobicycle.Business.StaticData;
import com.comsoftstar.autobicycle.Control.MyToolBar;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByDay;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByMonth;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByWeek;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.View.Interface.Iview.IWorkBy;
import com.comsoftstar.autobicycle.View.Main.Mine.Fragment3;
import com.comsoftstar.autobicycle.View.Presenter.PagePresenter;
import com.comsoftstar.autobicycle.databinding.ActivityDrivingCountBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

public class DrivingCountActivity extends BaseActivity<ActivityDrivingCountBinding> implements IWorkBy,View.OnClickListener{
    ActivityDrivingCountBinding mbinding;
    private PagePresenter<IWorkBy> pagePresenter;
//    public final static String[] months = new String[] { "Jan", "Feb", "Mar",
//            "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", };//X坐标标签
    private List<Float> num=new ArrayList<>();
    public ColumnChartView columnChart;
    public ColumnChartData columnData;
    @Override
    public int setLayoutId() {
        return R.layout.activity_driving_count;
    }

    @Override
    public void initView(ActivityDrivingCountBinding binding) {
        this.mbinding=binding;
        MyToolBar myToolBar=MyToolBar.newInstance();
        Toolbar toolbar=myToolBar.init(new Toolbar(this))
                .addTitle(getString(R.string.DrivingCount))
                .addHome(1, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        addToolBar(binding.drivercountlinearlayout,toolbar);
        pagePresenter=new PagePresenter<IWorkBy>(getApplication(),this);
        pagePresenter.getWorkStatisticsBy(0, StaticData.loginResults.get(0).getCfgID());
        columnChart=binding.barchart;
        binding.setOnclicklisteren(this);
        String lc=getIntent().getStringExtra("lc");
        String pb=getIntent().getStringExtra("pb");
        binding.grid.tvNumberlength.setText(lc);
        binding.drivingCount.tvNumberlength.setText(lc);
        binding.drivingCount.tvNumbertime.setText(pb);
    }


    /**
     * 初始化柱状图
     */
    private void initbarchart(String xlabel,Float[] doubles,String[] labels){
        columnChart.setInteractive(true);
        columnChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        int numColumns = labels.length;
        //label
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        // 列
        List<Column> columns = new ArrayList<Column>();
        //value
        List<SubcolumnValue> values;
       // Random random=new Random();
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            values.add(new SubcolumnValue(doubles[i].floatValue(), ChartUtils.pickColor()));
// 点击柱状图就展示数据量
            axisValues.add(new AxisValue(i).setLabel(labels[i]));//导入X标签列表

            columns.add(new Column(values).setHasLabelsOnlyForSelected(false)//点击显示数值
                    .setHasLabels(true));//常显示数值
        }

        columnData = new ColumnChartData(columns);//导入数据

        columnData.setAxisXBottom(new Axis(axisValues).setHasLines(true)
                .setTextColor(Color.WHITE)//设置X轴标签颜色
                .setName(xlabel));//设置X轴标题
        columnData.setAxisYLeft(new Axis().setHasLines(true)
                .setTextColor(Color.WHITE)//设置Y轴标签颜色
                .setMaxLabelChars(2)
                .setName("里程数"));//设置Y轴标题

        columnChart.setColumnChartData(columnData);//载入数据

// Set value touch listener that will trigger changes for chartTop.
        columnChart.setOnValueTouchListener(new ValueTouchListener());

// Set selection mode to keep selected month column highlighted.
        columnChart.setValueSelectionEnabled(false);//点击是否保持选中状态，(一直显示数值)

        columnChart.setZoomType(ZoomType.HORIZONTAL);
        columnChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        columnChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        Viewport v = new Viewport(columnChart.getMaximumViewport());
        v.left = -1;
        v.right= 16;//X轴显示的位置0-5 显示5个
        columnChart.setCurrentViewport(v);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rbutton1:
                pagePresenter.getWorkStatisticsBy(0, StaticData.loginResults.get(0).getCfgID());
                break;
            case R.id.rbutton2:
                pagePresenter.getWorkStatisticsBy(1, StaticData.loginResults.get(0).getCfgID());
                break;
            case R.id.rbutton3:
                pagePresenter.getWorkStatisticsBy(2, StaticData.loginResults.get(0).getCfgID());
                break;
        }
    }


    /**
     * 柱状图监听器
     */
    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex,
                                    SubcolumnValue value) {
            // Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();//点击柱状图触发事件
        }

        @Override
        public void onValueDeselected() {

            //Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();//点击柱状图以外触发事件

        }
    }



    @Override
    public void faileMsg(String msg) {
        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWorkDays(List<WorkStatisticsByDay> workStatisticsByDays) {
        List<String> list=new ArrayList<>();
        List<Float> list2=new ArrayList<>();
        for (int i = 0; i < workStatisticsByDays.size(); i++) {
            list.add(workStatisticsByDays.get(i).getWorkDate());
            list2.add(Float.valueOf(String.valueOf(workStatisticsByDays.get(i).getWorkMile())));
        }
        initbarchart("日期",list2.toArray(new Float[list2.size()]),list.toArray(new String[list.size()]));
    }

    @Override
    public void setWorkWeeks(List<WorkStatisticsByWeek> workStatisticsByWeeks) {
        List<String> list=new ArrayList<>();
        List<Float> list2=new ArrayList<>();
        for (int i = 0; i < workStatisticsByWeeks.size(); i++) {
            list.add(String.valueOf(workStatisticsByWeeks.get(i).getWeekNo()));
            list2.add(Float.valueOf(String.valueOf(workStatisticsByWeeks.get(i).getWorkMile())));
        }
        initbarchart("日期",list2.toArray(new Float[list2.size()]),list.toArray(new String[list.size()]));
    }

    @Override
    public void setWorkMonths(List<WorkStatisticsByMonth> workStatisticsByMonths) {
        List<String> list=new ArrayList<>();
        List<Float> list2=new ArrayList<>();
        for (int i = 0; i < workStatisticsByMonths.size(); i++) {
            list.add(String.valueOf(workStatisticsByMonths.get(i).getMonthNo()));
            list2.add(Float.valueOf(String.valueOf(workStatisticsByMonths.get(i).getWorkMile())));
        }
        initbarchart("日期",list2.toArray(new Float[list2.size()]),list.toArray(new String[list.size()]));
    }
}
