package com.company.sandeep.zipcodesearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.company.sandeep.pojo.Timezone;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by chsan_000 on 6/12/2016.
 */
public class TimeZoneActivity extends AppCompatActivity {
    private double lat, lang;
    TextView time, timeZone, countryName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timezone);
        time = (TextView) findViewById(R.id.current_time);
        timeZone = (TextView) findViewById(R.id.time_zone);
        countryName = (TextView) findViewById(R.id.country_name);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Time Zone");
        actionBar.setDisplayHomeAsUpEnabled(true);

        lat = getIntent().getDoubleExtra("langitude", 0);
        lang = getIntent().getDoubleExtra("lattitude", 0);

        loadJson(lat, lang);
    }

    private void loadJson(double lat, double lang) {
        RestApi restApi = new RestApi();

        restApi.getService().getTimezone(lat, lang, "deepak", new Callback<Timezone>() {
            @Override
            public void success(Timezone timezone, Response response) {
                time.setText(timezone.getTime());
                timeZone.setText(timezone.getTimezoneId());
                countryName.setText(timezone.getCountryName());
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(TimeZoneActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                Log.e("TimeZoneActivity", "failure - ");
            }
        });


    }
}
