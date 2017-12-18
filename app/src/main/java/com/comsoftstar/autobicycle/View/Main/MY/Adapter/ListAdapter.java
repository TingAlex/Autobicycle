package com.comsoftstar.autobicycle.View.Main.MY.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.comsoftstar.autobicycle.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/27.
 */

public class ListAdapter extends ArrayAdapter<My_Listitem> {
private int resourceId;
public ListAdapter(Context context, int textViewResourceId,
                   ArrayList<My_Listitem> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        }
@Override
public View getView(int position, View convertView, ViewGroup parent) {
        My_Listitem item = getItem(position); // 获取当前项的Fruit实例
        View view ;
        ViewHolder viewHolder;
        if (convertView == null) {                //判断是否有过缓存
        view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        viewHolder = new ViewHolder();
        viewHolder.image = (ImageView) view.findViewById
        (R.id.item_icon);
        viewHolder.title = (TextView) view.findViewById
        (R.id.item_title);
        view.setTag(viewHolder);                  //  将ViewHolder 存储在View 中
        } else {
        view = convertView;
        viewHolder = (ViewHolder) view.getTag(); //  重新获取ViewHolder
        }
        viewHolder.image.setImageResource(item.getImage());
        viewHolder.title.setText(item.getTitle());
        return view;
        }
class ViewHolder {
    ImageView image;
    TextView title;
}
}

