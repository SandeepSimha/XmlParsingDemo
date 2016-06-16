package com.example.chsan_000.xmlparsingdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chsan_000.pojo.Postalcodes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chsan_000 on 6/4/2016.
 */
public class PostAdapter extends BaseAdapter {

    List<Postalcodes> mList = new ArrayList<Postalcodes>();
    Context mContext;
    String TAG = "PostAdapter";

    public PostAdapter(Context context) {
        mContext = context;
    }

    public PostAdapter(Context context, int resource, List<Postalcodes> objects) {
        mContext = context;
        this.mList = objects;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Postalcodes getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.tem_file, parent, false);
        Postalcodes postalCode = mList.get(position);


        TextView category = (TextView) view.findViewById(R.id.txt_district);
        category.setText(postalCode.getAdminName2());

        TextView tv = (TextView) view.findViewById(R.id.txt_mandal);
        tv.setText(postalCode.getAdminName3());

        TextView price = (TextView) view.findViewById(R.id.txt_village);
        price.setText(postalCode.getPlaceName());

        TextView state = (TextView) view.findViewById(R.id.txt_state);
        state.setText(postalCode.getAdminName1());

        TextView country = (TextView) view.findViewById(R.id.txt_country);
        country.setText(postalCode.getCountryCode());

        TextView pinCode = (TextView) view.findViewById(R.id.txt_pin);
        pinCode.setText(postalCode.getPostalcode());



        return view;
    }
}
