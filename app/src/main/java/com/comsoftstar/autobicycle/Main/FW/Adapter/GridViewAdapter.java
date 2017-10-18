package com.comsoftstar.autobicycle.Main.FW.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.comsoftstar.autobicycle.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/26.
 */

public class GridViewAdapter extends BaseAdapter {
    public LayoutInflater inflater;
    public ArrayList<String> name;
    public ArrayList<Integer> image;

    public GridViewAdapter(Context context,ArrayList<String> name,ArrayList<Integer> image) {
        inflater = LayoutInflater.from(context);
        this.name = name;
        this.image=image;

    }
    @Override
    public int getCount() {
        return name.size();
    }

    @Override
    public Object getItem(int i) {
        return name.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;
        if(convertView == null)
        {
            view = inflater.inflate(R.layout.fuwu_item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.item_image);
            holder.textView = (TextView) view.findViewById(R.id.item_text);
            holder.line=(View)view.findViewById(R.id.bottomline);
            view.setTag(holder);
        }else
        {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        if (position>name.size()-1-name.size()%3)
            holder.line.setVisibility(View.INVISIBLE);
        holder.textView.setText(name.get(position).toString());
        holder.imageView.setImageResource(image.get(position));
        return view;
    }

    static class ViewHolder
    {
        ImageView imageView;
        TextView textView;
        View line;
    }
}