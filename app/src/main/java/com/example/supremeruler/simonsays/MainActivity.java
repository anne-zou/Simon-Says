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

    boolean gameOver = true;
    int score;
    int curButtonIndex;

    String whatToSay(int curButtonIndex) {
        return "Simon says: tap the " + color[curButtonIndex] + " button.";
    }

    void nextButton() {
        curButtonIndex = random.nextInt(4);
        simonSays.setText(whatToSay(curButtonIndex));
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

        // initialize Button objects
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
        } else {
            int idViewClicked = view.getId();

            if (idViewClicked == b[curButtonIndex].getId()) { // correct button clicked

                // update score
                ++score;
                scoreText.setText("" + score);

                nextButton();
                timer.start();

            } else {
                // wrong button clicked
                gameOver = true;
                timer.cancel();
                Toast.makeText(getApplicationContext(), "Wrong Button!", Toast.LENGTH_SHORT).show();
                simonSays.setText("Tap any button to play again.");
            }
        }
    }
}
