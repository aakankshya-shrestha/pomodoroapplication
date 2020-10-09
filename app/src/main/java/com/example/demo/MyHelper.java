package com.example.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {

    private static final String dbName = "demo";
    private static final int version = 1;

    public MyHelper(Context context){
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE STAT (_id INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, COUNT INTEGER)";
        db.execSQL(sql);
        insertManual("Sun", 2, db);
        insertManual("Mon", 3, db);
        insertManual("Tue",4, db);
        insertManual("Wed", 0, db);
        insertManual("Thu", 0, db);
        insertManual("Fri", 0, db);
        insertManual("Sat", 0, db);
    }


    public void insertManual(String date, int count, SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put("DATE", date);
        values.put("COUNT", count);
        db.insert("STAT", null, values);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
