package com.example.demo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main4Activity extends AppCompatActivity {

    public EditText dateone;
    public TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        MyHelper helper = new MyHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();



//        Calendar calendar = Calendar.getInstance();
//        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        Date d = new Date();
        String currentDate = sdf.format(d);
        //System.out.print(dayOfTheWeek);

        Cursor cursor = database.rawQuery("SELECT DATE, COUNT FROM STAT", new String[]{});
        if(cursor!= null){
            cursor.moveToFirst();
        }

        StringBuilder builder = new StringBuilder();

        do{
            String date = cursor.getString(0);
            Integer count = cursor.getInt(1);
            builder.append("DATE - " + date + "COUNT - " + count);


        }while(cursor.moveToNext());

        TextView textView = findViewById(R.id.showdemo);
        textView.setText(builder.toString());


        date = findViewById(R.id.textView4);
        date.setText(currentDate);




    }





}
