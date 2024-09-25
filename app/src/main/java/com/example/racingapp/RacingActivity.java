package com.example.racingapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;
import android.animation.ObjectAnimator;

public class RacingActivity extends AppCompatActivity {
    SeekBar horse1, horse2, horse3;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.racing_view);

        horse1 = findViewById(R.id.horse1);
        horse2 = findViewById(R.id.horse2);
        horse3 = findViewById(R.id.horse3);

        startButton = findViewById(R.id.btn_start);
        startButton.setOnClickListener(v -> startRace());
    }

    private void startRace() {
        ObjectAnimator animator1 = ObjectAnimator.ofInt(horse1, "progress", 0, 100);
        animator1.setDuration(5000 + (int)(Math.random() * 3000));
        animator1.start();

        ObjectAnimator animator2 = ObjectAnimator.ofInt(horse2, "progress", 0, 100);
        animator2.setDuration(5000 + (int)(Math.random() * 3000));
        animator2.start();

        ObjectAnimator animator3 = ObjectAnimator.ofInt(horse3, "progress", 0, 100);
        animator3.setDuration(5000 + (int)(Math.random() * 3000));
        animator3.start();
    }
}
