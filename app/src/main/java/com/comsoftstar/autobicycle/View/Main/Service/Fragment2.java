package com.comsoftstar.autobicycle.View.Main.Service;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.comsoftstar.autobicycle.Control.GlideImageLoader;
import com.comsoftstar.autobicycle.R;
import com.comsoftstar.autobicycle.Adapter.GridViewAdapter;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;

import static com.comsoftstar.autobicycle.App.App.context;

/**
 * Created by Administrator on 2017/9/25.
 */

public class Fragment2 extends Fragment {
    private GridView gridView;
    private GridViewAdapter gridViewadapter;

    //private CarouselView carouselView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment2, container, false);
        gridView = (GridView) rootview.findViewById(R.id.fuwu_gridview);
        gridView.setOnItemClickListener(itemClickListener);
        initUI();
        //carouselView = (CarouselView) rootview.findViewById(R.id.carouselView);
        Banner banner = (Banner) rootview.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        //资源文件
        final String[] images = {"http://lvmakeji.com/XYService/Images/1.jpg", "http://lvmakeji.com/XYService/Images/2.jpg", "http://lvmakeji.com/XYService/Images/3.jpg", "http://lvmakeji.com/XYService/Images/4.jpg"};
        banner.setImages(Arrays.asList(images));
        banner.start();
        // carouselView.setPageCount(images.length);
//        ImageListener imageListener = new ImageListener() {
//            @Override
//            public void setImageForPosition(int position, ImageView imageView) {
//                //imageView.setImageResource(sampleImages[position]);
//                Glide.with(context).load(images[position]).into(imageView);
//            }
//        };
        // carouselView.setImageListener(imageListener);


        return rootview;
    }

    private void initUI() {
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> image = new ArrayList<>();
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
        gridViewadapter = new GridViewAdapter(getContext(), name, image);
        gridView.setAdapter(gridViewadapter);
    }

    //网格子项点击事件
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                //网上报修
                case 0:
                    Intent intent = new Intent(getActivity(), RepairRecordActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
