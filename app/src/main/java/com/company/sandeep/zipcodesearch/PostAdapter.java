package com.company.sandeep.zipcodesearch;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.company.sandeep.pojo.Postalcodes;

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
        convertView = inflater.inflate(R.layout.tem_file, parent, false);

        final Postalcodes postalCode = mList.get(position);

        final TextView category = (TextView) convertView.findViewById(R.id.txt_district);
        category.setText(postalCode.getAdminName2());

        final TextView tv = (TextView) convertView.findViewById(R.id.txt_mandal);
        tv.setText(postalCode.getAdminName3());

        final TextView price = (TextView) convertView.findViewById(R.id.txt_village);
        price.setText(postalCode.getPlaceName());

        final TextView state = (TextView) convertView.findViewById(R.id.txt_state);
        state.setText(postalCode.getAdminName1());

        final TextView country = (TextView) convertView.findViewById(R.id.txt_country);
        country.setText(postalCode.getCountryCode());

        final TextView pinCode = (TextView) convertView.findViewById(R.id.txt_pin);
        pinCode.setText(postalCode.getPostalcode());

        ImageView imageShare = (ImageView) convertView.findViewById(R.id.img_share);
        ImageView imageMaps = (ImageView) convertView.findViewById(R.id.img_maps);
        ImageView imageTime = (ImageView) convertView.findViewById(R.id.img_time);
        ImageView imageCopy = (ImageView) convertView.findViewById(R.id.img_copy);

        imageShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Share
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,
                        "PinCode: " + pinCode.getText().toString() +
                                "\nCountry: " + country.getText().toString() +
                                "\nState: " + state.getText().toString() +
                                "\nVillage: " + price.getText().toString());
                intent.setType("text/plain");

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                mContext.startActivity(intent);
                Toast.makeText(mContext, "Shared Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        imageMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Mylocation.class);
                intent.putExtra("langitude", postalCode.getLng());
                intent.putExtra("lattitude", postalCode.getLat());
                intent.putExtra("placename", postalCode.getPlaceName());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
                Toast.makeText(mContext, "Show on maps clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        imageTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TimeZoneActivity.class);

                intent.putExtra("langitude", postalCode.getLng());
                intent.putExtra("lattitude", postalCode.getLat());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                mContext.startActivity(intent);
                Toast.makeText(v.getContext(), "TimeZone Clicked on maps clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        imageCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipBoard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData;
                clipData = ClipData.newPlainText("text", postalCode.getPostalcode() +
                        "\n" + postalCode.getCountryCode() +
                        "\n" + state.getText().toString() + "\n" + price.getText().toString());
                clipBoard.setPrimaryClip(clipData);
                Toast.makeText(mContext, "Text Copied to clipboard!", Toast.LENGTH_SHORT).show();

            }
        });

        return convertView;
    }
}
