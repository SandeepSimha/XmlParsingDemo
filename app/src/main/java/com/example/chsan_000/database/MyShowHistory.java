package com.example.chsan_000.database;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.chsan_000.xmlparsingdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chsan_000 on 6/10/2016.
 */
public class MyShowHistory extends AppCompatActivity {
    private MyHistoryAdapter empAdap;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button button;
    private List<PostPojo> employeeList = new ArrayList<>();

    final String TAG = "DatabaseViewRecordsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //actionBar.setIcon(R.drawable.cloud);
        actionBar.setTitle("History");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.myProgressBar);
        button = (Button) findViewById(R.id.my_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbOpenHelper dbOpenHelper = new DbOpenHelper(MyShowHistory.this);
                SQLiteDatabase db = dbOpenHelper.getWritableDatabase();


                db.execSQL("delete from " + dbOpenHelper.TABLE_NAME);

                db.close();

                empAdap.notifyDataSetChanged();

                Toast.makeText(MyShowHistory.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        empAdap = new MyHistoryAdapter(employeeList);

        recyclerView.setAdapter(empAdap);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);


        ZipCodeAsync databaseAsync = new ZipCodeAsync(this, progressBar, empAdap);
        databaseAsync.execute("Employee Records");
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_history_delete, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.delete_history) {
//            DbOpenHelper dbOpenHelper = new DbOpenHelper(this);
//            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
//            db.execSQL("delete from " + dbOpenHelper.TABLE_NAME);
//
//            Toast.makeText(MyShowHistory.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
