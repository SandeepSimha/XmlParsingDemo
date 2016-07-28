package com.example.chsan_000.database;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chsan_000.xmlparsingdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chsan_000 on 6/9/2016.
 */
public class MyHistoryAdapter extends RecyclerView.Adapter<MyHistoryAdapter.MyViewHolder> {
    private List<PostPojo> zipList;
    Context mContext;
    int resource;

    public MyHistoryAdapter(List<PostPojo> zipList, int resource, Context mContext) {
        this.mContext = mContext;
        this.zipList = zipList;
        this.resource = resource;
    }

    public void addZip(List<PostPojo> empployeeList) {
        zipList = new ArrayList<>(empployeeList);
        notifyDataSetChanged();// refresh adapter after modifying the list
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.history_name);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_activity, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(zipList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return zipList.size();
    }
}
