package com.example.supremeruler.simonsays;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView simonSays;
    TextView scoreText;
    TextView timeText;

    CountDownTimer timer;

    Random random = new Random();

    Button[] b = new Button[4];
    String[] color = {"pink", "blue", "purple", "green"};

    volatile boolean gameOver = true;
    volatile int score;
    volatile int curButtonIndex;

    String whatToSay(int curButtonIndex) {
        return "Simon says: tap the " + color[curButtonIndex] + " button.";
    }

    void nextButton() {
        curButtonIndex = random.nextInt(4); // pick next button
        simonSays.setText(whatToSay(curButtonIndex)); // update textview
    }

    void startNewGame() {
        gameOver = false;
        score = 0;
        scoreText.setText("" + score);
        nextButton();
        timer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize Button objects (these values should never change once assigned)
        b[0] = (Button) findViewById(R.id.Button1);
        b[1] = (Button) findViewById(R.id.Button2);
        b[2] = (Button) findViewById(R.id.Button3);
        b[3] = (Button) findViewById(R.id.Button4);

        // initialize TextView objects
        simonSays = (TextView) findViewById(R.id.simonSays);
        scoreText = (TextView) findViewById(R.id.score);
        timeText = (TextView) findViewById(R.id.time);

        // initialize the CountDownTimer
        timer = new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {
                // update the time
                String timeLeft = Long.toString(millisUntilFinished / 1000);
                timeText.setText("" + timeLeft);
            }
            public void onFinish() { // time's up!
                gameOver = true;
                timeText.setText("0");
                Toast.makeText(getApplicationContext(), "Time's Up!", Toast.LENGTH_SHORT).show();
                simonSays.setText("Tap any button to play again.");
            }
        };
        // Tap a button to start a new game.
    }

    public void onClick(View view){
        
        if (gameOver) {
            startNewGame();
            return;
        }
        
        // Get the id of the view tapped to see if it was the correct button
        int idOfViewTapped = view.getId();
        
        if (idOfViewTapped == b[curButtonIndex].getId()) { // the correct button was tapped!

            // update & display score
            ++score;
            scoreText.setText("" + score);

            // generate & display the next Simon Says instruction, and restart the timer
            nextButton();
            timer.start();
            
            return;
        }   
        
        // check if a wrong button was tapped
        for (int i = 0; i < 4; ++i) {
            if (i != curButtonIndex && idOfViewTapped == b[i].getId()) { // a wrong button was tapped!
                gameOver = true;
                timer.cancel();
                Toast.makeText(getApplicationContext(), "Wrong Button!", Toast.LENGTH_SHORT).show();
                simonSays.setText("Tap anywhere to play again.");
                return;
             }
        }
        
        // if the view tapped was not a button, do nothing
    }
    
}
