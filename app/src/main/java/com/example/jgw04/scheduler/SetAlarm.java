package com.example.jgw04.scheduler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static com.example.jgw04.scheduler.R.id.notification_background;
import static com.example.jgw04.scheduler.R.id.setButton;

public class SetAlarm extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setalarm);

        //set Onclick Listener {
        findViewById(R.id.setButton).setOnClickListener(this);
        findViewById(R.id.cancelButton).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        EditText editText = findViewById(R.id.alarm_title);
        TimePicker timePicker = findViewById(R.id.timePicker);

        //Set notification & text
        Intent intent = new Intent(SetAlarm.this, AlarmReceiver.class);
        intent.putExtra("notificationId", );
        intent.putExtra("todo", editText.getText().toString());

        // get Broadcast(context, requestCode, intent, flags)
        PendingIntent alarmIntent =  PendingIntent.getBroadcast(SetAlarm.this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        if ()
        switch (view.getID) {
            case R.id.setButton:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                //create time
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                //set alarm.
                //set (type, milliseconds, alarmintent);
                alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);

                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelButton:
                alarm.cancel(alarmIntent);
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
