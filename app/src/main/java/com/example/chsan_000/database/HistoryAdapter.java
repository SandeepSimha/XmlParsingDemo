package com.example.chsan_000.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chsan_000.xmlparsingdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chsan_000 on 6/6/2016.
 */
public class HistoryAdapter extends BaseAdapter {

    List<PostPojo> mList = new ArrayList<>();
    Context mContext;
    String TAG = "HistoryAdapter";

    public HistoryAdapter(Context context) {
        mContext = context;
    }

    public void addZipCode(List<PostPojo> zipList) {
        mList = new ArrayList<>(zipList);
        notifyDataSetChanged();// refresh adapter after modifying the list
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public PostPojo getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.history_activity, null);
        TextView txtViewId = (TextView) view.findViewById(R.id.history_name);

        PostPojo zip = getItem(position);
        //setting data
        txtViewId.setText(zip.getName());

        return convertView;
    }
}
