package com.example.supremeruler.simonsays;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button b1;
    Button b2;
    Button b3;
    Button b4;

    Button[] buttons = new Button[4];

    TextView time;
    CountDownTimer countDownTimer;

    int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view){
        int s = view.getId();
        if (isCorrect(view)) {
            score++;
            resetInstructions();
            resetTimer();
        } else {
            // Game Over
        }
    }

    boolean isCorrect(View view){
        return true;
    }

    void resetInstructions(){};
    void resetTimer(){};


}
