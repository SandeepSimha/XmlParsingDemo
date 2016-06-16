package com.example.chsan_000.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by chsan_000 on 6/6/2016.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    final String TAG = "DbOpenHelper";

    public static final String DATABASE_NAME = "postcode.db";
    public static final String TABLE_NAME = "postcodehistory";

    private String CREATE_TABLE = String.format("create table %s (name TEXT);", TABLE_NAME);
    public static String DELETE_TABLE = String.format("delete from ", TABLE_NAME);

    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public long insertZipCode(String zipCodeId) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", zipCodeId);

        long id = database.insert(TABLE_NAME, null, values);
        // this will return
        // long datatype
        // values

        if (id < 0) {
            Log.e(TAG, "insertEmployee: employee data insertion failed !");
        } else {
            Log.i(TAG, "insertEmployee: employee data insertion Successfully done !");
        }

        return id;
    }

    public void deleteContact() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
       // db.close();
    }
}
