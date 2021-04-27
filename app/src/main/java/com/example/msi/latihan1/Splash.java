package com.example.msi.latihan1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.msi.latihan1.ui.login.LoginActivity;

public class Splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView babi = (ImageView) findViewById(R.id.babi);
        Animation fadeAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        babi.startAnimation(fadeAnim);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent homeintent = new Intent(Splash.this, LoginActivity.class);
                startActivity(homeintent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}