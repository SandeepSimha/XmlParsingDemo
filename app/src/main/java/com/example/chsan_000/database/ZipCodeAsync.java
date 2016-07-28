package com.example.chsan_000.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chsan_000 on 6/9/2016.
 */
public class ZipCodeAsync extends AsyncTask<String, Integer, List<PostPojo>> {
    Context context;
    MyHistoryAdapter adapter;
    ProgressBar progressBar;

    public ZipCodeAsync(Context context, ProgressBar progressBar, MyHistoryAdapter adapter) {
        this.context = context;
        this.progressBar = progressBar;
        this.adapter = adapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
        //Toast.makeText(context, "Databse Started", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected List<PostPojo> doInBackground(String... params) {
        String value = params[0];
        Log.e("DatabaseSync", "doInBackGroung: parametrr - " + value);
        //return getEmployeeRecords();
        List<PostPojo> zipList = new ArrayList<>();
        // fill the list using database
        DbOpenHelper dbOpenHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DbOpenHelper.TABLE_NAME, null);// query//it returns cursor
        //
        int progress = 0;
        if (cursor.moveToFirst()) {
            if (cursor.getCount() <= 0 || cursor == null) {
                Toast.makeText(context, "No records yet!", Toast.LENGTH_SHORT).show();
            } else {
                do {
                    progress++;
                    int idIndex = cursor.getColumnIndex("name");
                    String id = cursor.getString(idIndex);
                    PostPojo emp = new PostPojo();
                    emp.setName(id);
                    // add to list
                    zipList.add(emp);
                    //to knw how many resordds
                    publishProgress(progress);
                } while (cursor.moveToNext());
            }
        }
        return zipList;
    }

    @Override
    protected void onPostExecute(List<PostPojo> postPojos) {
        super.onPostExecute(postPojos);
        // Toast.makeText(context, "Database Ended", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        adapter.addZip(postPojos);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int progress = values[0];
        Log.e("AsyncTask", "OnprogressUpdate: record recived " + progress);
    }
}
