package com.example.blog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends BaseActivity {
    private boolean isFirstRun;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("share", MODE_PRIVATE);
        //默认false
        isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);

        Thread startThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1500);
                    if (isFirstRun) {
                        Intent goGuide = new Intent(getApplicationContext(), GuideActivity.class);
                        startActivity(goGuide);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isFirstRun", false);
                        editor.commit();
                        finish();
                    }
                    else {
                        Intent goAd = new Intent(getApplicationContext(), adActivity.class);
                        startActivity(goAd);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        startThread.start();
    }
}
