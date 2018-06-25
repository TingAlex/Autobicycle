package com.comsoftstar.autobicycle.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comsoftstar.autobicycle.Model.Bean.CallBack.Main.RepairRecordBean;
import com.comsoftstar.autobicycle.Model.Bean.CallBack.Register.SalePoint;
import com.comsoftstar.autobicycle.R;

import java.util.List;

/**
 * Created by SJ on 2018/6/25.
 */

public class RepairRecordAdapter extends RecyclerView.Adapter<RepairRecordAdapter.ViewHolder> {
    private Context context;
    private List<RepairRecordBean> items;
    public RepairRecordAdapter(Context context, List<RepairRecordBean> items){
        this.context=context;
        this.items=items;
    }
    public void setdata( List<RepairRecordBean> items){
        this.items=items;
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_feedback;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_feedback=(TextView)itemView.findViewById(R.id.tv_feedback);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_feedback,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_feedback.setText(items.get(position).getPointName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
