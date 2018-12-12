package com.example.jgw04.scheduler;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

public class RingtonePlayingService extends Service {
    boolean isRunning;
    MediaPlayer media_song;
    int startid;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {
        Log.i("LocalService", "Received start id " + startid + ": " + intent);

        String state = intent.getExtras().getString("extra");

        Log.e("Ringtone state:extra is", state);

        assert state != null;
        switch (state) {
            case "alarm on":
                startid = 1;
                break;
            case "alarm off":
                startid = 0;
                Log.e("Start ID is ", state);
                break;
            default:
                startid = 0;
                break;
        }

        if (!this.isRunning && startid == 1) {
            media_song = MediaPlayer.create(this, R.raw.alarmbell);
            media_song.start();
            this.isRunning = true;
            this.startid = 0;

            NotificationManager notify = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Intent intent_main = new Intent(this.getApplicationContext(), MainActivity.class);
            PendingIntent pending_main_act = PendingIntent.getActivity(this, 0, intent_main, 0);
            Notification popup = new Notification.Builder(this)
                    .setContentTitle("Time to take medicine!")
                    .setContentText("Don't forget")
                    .setContentIntent(pending_main_act)
                    .setAutoCancel(true)
                    .build();
            notify.notify(0, popup);


        }
        else if (this.isRunning && startid == 0) {
            media_song.stop();
            media_song.reset();
            this.isRunning = false;
            this.startid = 0;

        }
        else if (!this.isRunning && startid == 0) {
            this.isRunning = false;
            this.startid = 0;

        }
        else if (this.isRunning && startid == 1) {
            this.isRunning = true;
            this.startid = 1;
        }
        else {

        }
        media_song = MediaPlayer.create(this, R.raw.alarmbell);
        media_song.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Cancel the persistent notification.
        // Tell the user we stopped.
        super.onDestroy();
        this.isRunning = false;
    }
}
