package com.example.msi.latihan1;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class SongActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        mediaPlayer = MediaPlayer.create(this,R.raw.closetothesun);
    }

    public void startmusic(View v){

        mediaPlayer.start();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

            }
        };
        timer.schedule(timerTask,0,3000);
    }
    public void stopmusic(View v){
        if(mediaPlayer!=null)
        {
            mediaPlayer.stop();
            mediaPlayer.prepareAsync();
        }
    }
}
