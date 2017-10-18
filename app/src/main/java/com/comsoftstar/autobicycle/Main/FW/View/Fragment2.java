package com.comsoftstar.autobicycle.Main.FW.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.comsoftstar.autobicycle.Main.FW.Adapter.GridViewAdapter;
import com.comsoftstar.autobicycle.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/25.
 */

public class Fragment2 extends Fragment {
    private GridView gridView;
    private GridViewAdapter gridViewadapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.fragment2,container,false);
        gridView=(GridView)rootview.findViewById(R.id.fuwu_gridview);
        initUI();
        return rootview;
    }
    private void initUI(){
        ArrayList<String> name=new ArrayList<>();
        ArrayList<Integer> image=new ArrayList<>();
        name.add("在线报修");
        image.add(R.drawable.raw_1504242252);
        name.add("中控设置");
        image.add(R.drawable.raw_1504242324);
        name.add("服务网点");
        image.add(R.drawable.raw_1504242369);
        name.add("使用常识");
        image.add(R.drawable.raw_1504242406);
        name.add("客服");
        image.add(R.drawable.raw_1504242518);
        gridViewadapter = new GridViewAdapter(getContext(), name,image);
        gridView.setAdapter(gridViewadapter);
    }
}
