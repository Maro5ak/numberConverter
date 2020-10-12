package com.ptasek.convertnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Setup thresholds for Marks here, if U wish to have different thresholds
    private final int firstThreshold = 87;
    private final int secondThreshold = 73;
    private final int thirdThreshold = 58;
    private final int fourthThreshold = 44;

    Number number = new Number();

    TextView task;
    EditText result;
    Chronometer timer;

    private String[] tasks = new String[12];
    private int correct = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //After a press of a button, this triggers, fills tasks with numbers from 3 different systems\
    //1. Binary; 2. Hexadecimal; 3. Decimal
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
            tasks[i] = (String)task.getText();
        }
    }

    // the method Button won't trigger anything unless there's First name, Last name and class filled
    public void startTimer(View v){
        long endTime;
        Button startButton = (Button)v;
        timer = findViewById(R.id.timer);
        final TextView name = findViewById(R.id.firstName);
        final TextView lastName = findViewById(R.id.lastName);
        final TextView schoolClass = findViewById(R.id.schoolClass);
        if(name.getText().toString().isEmpty() || lastName.getText().toString().isEmpty() || schoolClass.getText().toString().isEmpty()){
            name.setHintTextColor(Color.RED);
            lastName.setHintTextColor(Color.RED);
            schoolClass.setHintTextColor(Color.RED);
            new CountDownTimer(3000, 3) {
                @Override
                public void onTick(long l) { }
                @Override
                public void onFinish() {
                    name.setHintTextColor(Color.parseColor("#E1E1E1"));
                    lastName.setHintTextColor(Color.parseColor("#E1E1E1"));
                    schoolClass.setHintTextColor(Color.parseColor("#E1E1E1"));
                }
            }.start();
        }
        // starts the timer
        else if(startButton.getText().equals("start")) {
            init();
            startButton.setText(R.string.buttonStop);
            timer.setBase(SystemClock.elapsedRealtime());
            timer.start();
        }
        // stops the timer and evaluates answers
        else {
            timer.stop();
            endTime = SystemClock.elapsedRealtime();
            startButton.setText(R.string.buttonStart);
            getResults();
            TextView markText = findViewById(R.id.successRate);
            markText.setText(getMark(endTime - timer.getBase()));
        }
    }

    // Very messy method that gets results, sets color to either red or green and increments correct answers
    @SuppressLint("SetTextI18n")
    private void getResults(){
        for(int i = 0; i < 24; i++){
            String conversion;
            String id = "result" + i;
            int resId = getResources().getIdentifier(id, "id", getPackageName());
            result = findViewById(resId);
            String answer = String.valueOf(result.getText());
            // basically, each task is converted to the other 2 groups, so that means for 1 input, 2 answers. Either Even or Odd numbered answers
            if(i <= 7) conversion = i % 2 == 0 ? "hex2bin" : "dec2bin";
            else if(i <= 15) conversion = i % 2 == 0 ? "dec2hex" : "bin2hex";
            else conversion = i % 2 == 0 ? "hex2dec" : "bin2dec";

            //workaround for only 12 inputs and 24 answers
            //and again, even or odd numbered answers evaluation
            if(i % 2 == 0) {
                if(number.evaluate(tasks[i/2], answer, conversion)){
                    result.setTextColor(Color.GREEN);
                    correct++;
                }
                else{
                    result.setTextColor(Color.RED);
                }
            }
            else {

                if(number.evaluate(tasks[(i-1)/2], answer, conversion)){
                    result.setTextColor(Color.GREEN);
                    correct++;
                }
                else{
                    result.setTextColor(Color.RED);
                }
            }
        }
        TextView correctAnswers = findViewById(R.id.answersCorrect);
        correctAnswers.setText(correct + "/24");
    }

    // Marks the "student" according to their success percentage
    private String getMark(long elapsedTime){
        if(elapsedTime < 300000){
            int success = (correct/24)*100;
            if(success > firstThreshold) return success + "%; 1";
            else if(success > secondThreshold) return success + "%; 2";
            else if(success > thirdThreshold) return success + "%; 3";
            else if(success > fourthThreshold) return success + "%; 4";
            else return success + "%; 5";
        }
        else return "time's out; 5";
    }
}