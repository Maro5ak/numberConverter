package com.ptasek.convertnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Number number = new Number();
    TextView task;
    Chronometer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = findViewById(R.id.timer);
        init();
    }

    private void init(){

        for(int i = 0; i < 12; i++) {
            String id = "task" + i;
            int resId = getResources().getIdentifier(id, "id", getPackageName());
            task = findViewById(resId);
            // 0, 1, 2, 3
            if(i <= 3){
                task.setText(number.dec2bin());
            }
            // 4, 5, 6, 7
            else if(i <= 7){
                task.setText(number.dec2hex());
            }
            // 8, 9, 10, 11
            else{
                task.setText(number.dec());
            }
        }
    }


    public void startTimer(View v){
        long startTime, endTime;
        Button startButton = (Button)v;
        if(startButton.getText().equals("start")) {
            startButton.setText(R.string.buttonStop);
            startTime = SystemClock.elapsedRealtime();
            timer.start();
            timer.setBase(startTime);
        }
        else {
            timer.stop();
            endTime = SystemClock.elapsedRealtime();
            startButton.setText(R.string.buttonStart);
        }
    }




}