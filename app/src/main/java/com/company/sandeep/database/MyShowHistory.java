package com.company.sandeep.database;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.company.sandeep.zipcodesearch.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chsan_000 on 6/10/2016.
 */
public class MyShowHistory extends AppCompatActivity {
    private MyHistoryAdapter empAdap;
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ActionBar actionBar;
    List<PostPojo> employeeList = new ArrayList<>();

    private static final String TAG = "MyShowHistory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        //actionBar.setIcon(R.drawable.cloud);
        actionBar.setTitle("History");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.myProgressBar);


        recyclerView.setLayoutManager(new LinearLayoutManager(MyShowHistory.this));
        recyclerView.setHasFixedSize(true);

        empAdap = new MyHistoryAdapter(employeeList, R.layout.activity_recycle, MyShowHistory.this);
        empAdap.notifyDataSetChanged();
        recyclerView.setAdapter(empAdap);

        ZipCodeAsync databaseAsync = new ZipCodeAsync(this, progressBar, empAdap);
        databaseAsync.execute("Employee Records");
        empAdap.addZip(employeeList);

        empAdap.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_history) {

            DbOpenHelper dbOpenHelper = new DbOpenHelper(this);
            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
            db.execSQL("delete from " + dbOpenHelper.TABLE_NAME);

            empAdap.notifyDataSetChanged();

            empAdap = new MyHistoryAdapter(employeeList, R.layout.activity_recycle, MyShowHistory.this);
            empAdap.notifyDataSetChanged();
            recyclerView.setAdapter(empAdap);

            Toast.makeText(MyShowHistory.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
