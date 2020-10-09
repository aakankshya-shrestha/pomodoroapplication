package com.example.demo;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static long START_TIME_IN_MILLIS = 120000;
    private static long BREAK_TIME_IN_MILLIS = 300000;

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private Button sButtonPlus;
    private Button sButtonMinus;
    private Button iButtonSendData;
    WifiManager wifimanager;
    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //database connection part helper for update
        iButtonSendData = findViewById(R.id.addSession);

        iButtonSendData.setVisibility(View.INVISIBLE);

        iButtonSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });


        editTimer();

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();


                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();
    }


    private void editTimer(){
        mTextViewCountDown = findViewById(R.id.textView);

        mButtonStartPause = findViewById(R.id.button);
        mButtonReset = findViewById(R.id.button2);

        sButtonPlus = findViewById(R.id.button3);
        sButtonMinus = findViewById(R.id.button4);

        sButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one_updateCountDownText();

            }
        });

        sButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                two_updateCountDownText();

            }
        });

    }

    private void startTimer() {

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                DisableWiFi();

            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
                iButtonSendData.setVisibility(View.VISIBLE);
                EnableWiFi();
                breakTime();


            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("Pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }


    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }


    private void one_updateCountDownText() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS + 300000;
        updateCountDownText();
    }

    private void two_updateCountDownText() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS - 300000;
        updateCountDownText();
    }


    public void EnableWiFi() {

        wifimanager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifimanager.setWifiEnabled(true);

    }

    public void DisableWiFi() {

        wifimanager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifimanager.setWifiEnabled(false);

    }
    public void seeStat(View view){
        Intent intent = new Intent (this, Main2Activity.class);
        startActivity(intent);

    }

    public void sendData(View view){
        Intent intent = new Intent(this, Main4Activity.class);
        startActivity(intent);

    }

    public void editMusic(View view){
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }

    public void breakTime(){
        mTimeLeftInMillis = BREAK_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
        //iButtonSendData.setVisibility(View.INVISIBLE);
        EnableWiFi();



    }
//
//    public void checkAdd(){
//        mButtonReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addData();
//
//            }
//        });
//    }

    public void addData(){
        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);

        MyHelper helper = new MyHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT DATE, COUNT FROM STAT", new String[]{});
        if(cursor!= null){
            cursor.moveToFirst();
        }

        do{
        String date = cursor.getString(0);
        Integer count = cursor.getInt(1);
        if(date.equals(dayOfTheWeek)){
            ContentValues values = new ContentValues();
            values.put("COUNT", count+1);
            database.update("STAT", values, "DATE = ?", new String[]{dayOfTheWeek});

        }

        }while(cursor.moveToNext());
        iButtonSendData.setVisibility(View.INVISIBLE);


//        ContentValues values = new ContentValues();
//        values.put("COUNT", 7);
//        database.update("STAT", values, "DATE = ?", new String[]{"Wed"});
    }




}





