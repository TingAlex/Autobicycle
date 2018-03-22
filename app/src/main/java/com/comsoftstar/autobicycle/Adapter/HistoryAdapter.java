package com.comsoftstar.autobicycle.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comsoftstar.autobicycle.Model.Bean.Historyitem;
import com.comsoftstar.autobicycle.R;

import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context mcontext;
    private List<Historyitem> mlistt;

    static class ViewHolder extends RecyclerView.ViewHolder {
        //  View listview;
        CardView cardView;
        TextView listdate;
        TextView listtime;
        TextView liststart;
        TextView listend;
        public ViewHolder(View view) {
            super(view);
            //  listview=view;
            cardView = (CardView) view;
            listdate = (TextView) view.findViewById(R.id.date);
            listtime = (TextView) view.findViewById(R.id.time);
            liststart = (TextView) view.findViewById(R.id.start);
            listend= (TextView) view.findViewById(R.id.end);
        }
    }

    public HistoryAdapter(List<Historyitem> listt) {
        mlistt = listt;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mcontext == null) {
            mcontext=parent.getContext();
        }
        View view= LayoutInflater.from(mcontext).inflate(R.layout.drivinghistory_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Historyitem historyitem=mlistt.get(position);
        holder.listdate.setText(historyitem.getDate());
        holder.listtime.setText(historyitem.getTime());
        holder.liststart.setText(historyitem.getStart());
        holder.listend.setText(historyitem.getEnd());

    }

    @Override
    public int getItemCount() {
        return mlistt.size();
    }
}
