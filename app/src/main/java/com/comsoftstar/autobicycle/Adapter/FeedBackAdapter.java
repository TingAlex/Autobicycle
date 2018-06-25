package com.comsoftstar.autobicycle.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comsoftstar.autobicycle.Model.Bean.CallBack.Main.FeedBack;
import com.comsoftstar.autobicycle.R;

import java.util.List;

/**
 * Created by SJ on 2018/6/25.
 */

public class FeedBackAdapter extends RecyclerView.Adapter<FeedBackAdapter.ViewHolder> {
    private Context context;
    private List<FeedBack> items;
    public FeedBackAdapter(Context context, List<FeedBack> items){
        this.context=context;
        this.items=items;
    }
    public void setdata( List<FeedBack> items){
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
        holder.tv_feedback.setText(items.get(position).getSuggestion());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
