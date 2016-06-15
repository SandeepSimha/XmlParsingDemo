package com.example.chsan_000.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.chsan_000.xmlparsingdemo.R;

/**
 * Created by chsan_000 on 6/6/2016.
 */
public class ShowHistory extends AppCompatActivity {

    ListView listView;
    HistoryAdapter historyAdapter;
    ProgressBar progressBar;

    final String TAG = "ShowHistoryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        Log.e(TAG, "onCreate: called - ");
        historyAdapter = new HistoryAdapter(this);

        listView = (ListView) findViewById(R.id.listViewHistory);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listView.setAdapter(historyAdapter);

        //ZipCodeAsync databaseAsync = new ZipCodeAsync(this, progressBar, historyAdapter);
        //databaseAsync.execute("Zip Records");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
