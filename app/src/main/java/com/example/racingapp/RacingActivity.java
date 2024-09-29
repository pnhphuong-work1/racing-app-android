package com.example.racingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RacingActivity extends AppCompatActivity {
    SeekBar horse1, horse2, horse3;
    Button startButton;
    HashMap<Integer,Integer> selectedHorse = new HashMap<>();
    int betResult = 0;
    final ArrayList<Integer> raceOrder = new ArrayList<>(); // Lưu thứ tự các con ngựa


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.racing_view);
        //Initialize the views
        Projecting();
        //Start the race
        startButton.setOnClickListener(v -> {
            startButton.setEnabled(false);
            startRace();
        });
    }

    private void startRace() {
        horse1.setProgress(0);
        horse2.setProgress(0);
        horse3.setProgress(0);
        betResult = 0;
        //Start the timer
        final CountDownTimer timer = new CountDownTimer(30000, 300) {
            @Override
            public void onTick(long l) {
                int distanceHorse1 = (int) (Math.random() * 10);
                int distanceHorse2 = (int) (Math.random() * 10);
                int distanceHorse3 = (int) (Math.random() * 10);
                horse1.setProgress(horse1.getProgress() + distanceHorse1);
                horse2.setProgress(horse2.getProgress() + distanceHorse2);
                horse3.setProgress(horse3.getProgress() + distanceHorse3);
                Log.d("RaceDebug", "Race Order Size: " + raceOrder.size() +
                        ", Horse 1: " + horse1.getProgress() +
                        ", Horse 2: " + horse2.getProgress() +
                        ", Horse 3: " + horse3.getProgress());
             /*   if (horse1.getProgress() >= horse1.getMax()) {
                    int res = finishRace(1);
                    Toast.makeText(RacingActivity.this
                            , "Horse 1 wins, "+ res, Toast.LENGTH_SHORT).show();
                    this.cancel();
                } else if (horse2.getProgress() >= horse2.getMax()) {
                    int res = finishRace(2);
                    Toast.makeText(RacingActivity.this
                            , "Horse 2 wins, "+ res, Toast.LENGTH_SHORT).show();
                    this.cancel();
                } else if (horse3.getProgress() >= horse3.getMax()) {
                    int res = finishRace(3);
                    Toast.makeText(RacingActivity.this
                            , "Horse 3 wins, "+ res, Toast.LENGTH_SHORT).show();
                    this.cancel();
                }*/

                if (horse1.getProgress() >= horse1.getMax() && !raceOrder.contains(1)) {
                    raceOrder.add(1); // Ngựa 1 về đích
                    Toast.makeText(RacingActivity.this
                            , "black Horse  wins, ", Toast.LENGTH_SHORT).show();
                }
                if (horse2.getProgress() >= horse2.getMax() && !raceOrder.contains(2)) {
                    raceOrder.add(2); // Ngựa 2 về đích
                    Toast.makeText(RacingActivity.this
                            , "White Horse 2 wins, ", Toast.LENGTH_SHORT).show();
                }
                if (horse3.getProgress() >= horse3.getMax() && !raceOrder.contains(3)) {
                    raceOrder.add(3); // Ngựa 3 về đích
                    Toast.makeText(RacingActivity.this
                            , " Brown Horse 3 wins, ", Toast.LENGTH_SHORT).show();
                }

                if (raceOrder.size() == 3) { // Khi tất cả ngựa đã về đích
                    this.cancel();
                    finishRace(raceOrder);
                }
            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();
    }

    private void  finishRace(ArrayList<Integer> raceOrder){
        for (Map.Entry<Integer, Integer> entry : selectedHorse.entrySet()){
            if(entry.getKey() == raceOrder.get(0))
                betResult += entry.getValue();
            else
                betResult -= entry.getValue();
        }
        startButton.setEnabled(true);
        Intent intent = new Intent(RacingActivity.this, MainActivity.class);
        intent.putExtra("betResult", betResult);
        intent.putExtra("firstPlace", raceOrder.get(0));
        intent.putExtra("secondPlace", raceOrder.get(1));
        intent.putExtra("thirdPlace", raceOrder.get(2));
        startActivity(intent);

        //return betResult;
    }

    private void Projecting(){
        Intent intent = getIntent();

        //Find the horse views
        horse1 = findViewById(R.id.horse1);
        horse2 = findViewById(R.id.horse2);
        horse3 = findViewById(R.id.horse3);
        selectedHorse.put(1,100);
        selectedHorse.put(2,200);
        selectedHorse.put(3,500);

        startButton = findViewById(R.id.btn_start);
    }
}
