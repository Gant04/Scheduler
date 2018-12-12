package com.example.jgw04.scheduler;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends Activity{
    //get current time in milliseconds
    long now = System.currentTimeMillis();
    //save current time in date
    Date date = new Date(now);
    //set format to show time
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    String formatDate = sdfNow.format(date);

    TextView dateNow;

    @Override
    protected void Oncreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        dateNow = (TextView) findViewById(R.id.dateNow);
        dateNow.setText(formatDate);
    }
}
