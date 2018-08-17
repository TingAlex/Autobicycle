package com.comsoftstar.autobicycle.View.Main.Mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.comsoftstar.autobicycle.Business.StaticData;
import com.comsoftstar.autobicycle.Model.Bean.WorkStatisticsByMonth;
import com.comsoftstar.autobicycle.Util.RegexUtil;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.Adapter.ListAdapter;
import com.comsoftstar.autobicycle.Model.Bean.My_Listitem;
import com.comsoftstar.autobicycle.View.Interface.Iview.IFragment3View;
import com.comsoftstar.autobicycle.View.Presenter.PagePresenter;

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

/**
 * Created by Administrator on 2017/9/25.
 */

public class Fragment3 extends Fragment implements IFragment3View {
    private ListView listView;
    private ListAdapter listAdapter;
    private ArrayList<My_Listitem> listt = new ArrayList<>();
    private My_Listitem my_listitem;
    private TextView myphone;
    private List<Float> num = new ArrayList<>();
    public ColumnChartView columnChart;
    public ColumnChartData columnData;
    private PagePresenter<IFragment3View> pagePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment3, container, false);
        myphone = (TextView) rootview.findViewById(R.id.myphone);
        listView = (ListView) rootview.findViewById(R.id.my_listview);
        columnChart = (ColumnChartView) rootview.findViewById(R.id.barchart);

        listView.setOnItemClickListener(itemClickListener);
        initUI();
        pagePresenter = new PagePresenter<IFragment3View>(getActivity().getApplicationContext(), this);
        pagePresenter.getWorkStatisticsBy(2, StaticData.loginResults.get(0).getCfgID());

        return rootview;
    }

    private void initUI() {
        myphone.setText(RegexUtil.phoneNoHide("18068261236"));
        listt.clear();
        my_listitem = new My_Listitem(R.drawable.ic_directions_bike_black_24dp, "车辆信息");
        listt.add(my_listitem);
        my_listitem = new My_Listitem(R.drawable.raw_1504239659, "意见反馈");
        listt.add(my_listitem);
        my_listitem = new My_Listitem(R.drawable.raw_1504239668, "设置");
        listt.add(my_listitem);
        my_listitem = new My_Listitem(R.drawable.raw_1504239673, "关于");
        listt.add(my_listitem);
        listAdapter = new ListAdapter(getContext(), R.layout.item_my, listt);
        listView.setAdapter(listAdapter);


    }

    /**
     * 初始化柱状图
     */
    private void initbarchart(String xlabel, Float[] floats, String[] labels) {
        columnChart.setInteractive(true);
        columnChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        int numColumns = labels.length;

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        Random random = new Random();
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            values.add(new SubcolumnValue(floats[i].floatValue(), ChartUtils.pickColor()));
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
                .setName("里程"));//设置Y轴标题

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
        v.right = 16;//X轴显示的位置0-5 显示5个
        columnChart.setCurrentViewport(v);
    }

    @Override
    public void faileMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWorkMonths(List<WorkStatisticsByMonth> workStatisticsByMonths) {
        List<String> list = new ArrayList<>();
        List<Float> list2 = new ArrayList<>();
        for (int i = 0; i < workStatisticsByMonths.size(); i++) {
            list.add(String.valueOf(workStatisticsByMonths.get(i).getMonthNo()));
            list2.add(Float.valueOf(String.valueOf(workStatisticsByMonths.get(i).getWorkMile())));
        }
        initbarchart("月次", list2.toArray(new Float[list2.size()]), list.toArray(new String[list.size()]));
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

    /**
     * listview子项监听事件
     */
    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 1:
                    Intent intent = new Intent(getActivity(), FeedBackActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    Intent intent2 = new Intent(getActivity(), SettingActivity.class);
                    startActivity(intent2);
                    break;
                case 3:
                    Intent intent3 = new Intent(getActivity(), AboutActivity.class);
                    startActivity(intent3);
                    break;
            }

        }
    };
}
