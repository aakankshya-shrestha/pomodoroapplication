package com.example.demo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class Main2Activity extends AppCompatActivity {
    private GraphView graph;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ArrayList<Integer> dbYvalue = new ArrayList<Integer>();
        MyHelper helper = new MyHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT DATE, COUNT FROM STAT", new String[]{});

        if(cursor!= null){
            if  (cursor.moveToFirst()) {
                do { dbYvalue.add(cursor.getInt(1));
                }while (cursor.moveToNext());
            }
        }
        // double price = cursor.getDouble(1);







        LineChartView lineChartView = findViewById(R.id.chart);
        //initialize x and y axis
        String[] axisData = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
//        int[] yAxisData = {1,4,2,4,2,4,3};

        //Object[] yAxisData = dbYvalue.toArray();

        Integer[] yAxisData = new Integer[dbYvalue.size()];
        yAxisData = dbYvalue.toArray(yAxisData);
        //hold the data for Axis and Y-Axis.
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();
        //Declare and initialize the line which appears inside graph chart, this line will hold the values of Y-Axis.
        Line line = new Line(yAxisValues);
        //add Axis and Y-Axis data inside yAxisValues and axisValues lists.
        for(int i = 0; i < axisData.length; i++){
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++){
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }

        //This list will hold the line of the graph chart.
        List lines = new ArrayList();
        lines.add(line);
        // add the graph line to the overall data chart.
        LineChartData data = new LineChartData();
        data.setLines(lines);
        lineChartView.setLineChartData(data);
        //Axis values
        Axis axis = new Axis();
        axis.setValues(axisValues);
        data.setAxisXBottom(axis);
        //Y axis values
        Axis yAxis = new Axis();
        data.setAxisYLeft(yAxis);
        axis.setTextSize(16);
        axis.setTextColor(Color.parseColor("#039BE5"));
        line.setColor(Color.parseColor("#aa8ead"));
        yAxis.setTextColor(Color.parseColor("#039BE5"));
        yAxis.setTextSize(16);

    }


    }

