package com.example.racingapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int betResult, balance, firstPlace, secondPlace, thirdPlace;
    String username;
    Button btnRestart, btnExit;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.result_view);

        Projecting();

        // Set hình ảnh của ngựa về nhất
        TextView tv1stPlace = findViewById(R.id.tv1stPlace);
        tv1stPlace.setBackgroundResource(getHorseImage(firstPlace)); // Set corresponding horse image
        // Set hình ảnh của ngựa về nhì
        TextView tv2ndPlace = findViewById(R.id.tv_2nd_place);
        tv2ndPlace.setBackgroundResource(getHorseImage(secondPlace));
        // Set hình ảnh của ngựa về ba
        TextView tv3rdPlace = findViewById(R.id.tv_3rd_place);
        tv3rdPlace.setBackgroundResource(getHorseImage(thirdPlace));

        // Hiển thị kết quả cá cược
        TextView resultTextView = findViewById(R.id.result_text);
        resultTextView.setText(betResult > 0 ? "YOU WIN "+betResult+"!" : "YOU LOSE "+betResult+"!");
        balance += betResult;
        // Hiển thị số dư hiện tại
        TextView currentBalance = findViewById(R.id.current_balance);
        currentBalance.setText("Balance: "+balance);

        if (betResult > 0) {
            playSound(R.raw.win_sound);
        } else {
            playSound(R.raw.lose_sound);
        }

        int finalBalance = balance;
        btnRestart.setOnClickListener(view -> {
          Intent intent = new Intent(MainActivity.this, BettingActivity.class);
          intent.putExtra("balance", finalBalance);
          intent.putExtra("name", username);
          Log.d("MainActivity", "Put Extra Username: " + username);
          //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
          finish();
      });
        btnExit.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        });
    }

    private int getHorseImage(int horseNumber) {
        switch (horseNumber) {
            case 1:
                return R.drawable.horse1; // Image for horse 1
            case 2:
                return R.drawable.horse2; // Image for horse 2
            case 3:
                return R.drawable.horse3; // Image for horse 3
         default:
               return R.drawable.horse3;// Default image for unknown horses
        }
    }
    private void Projecting(){
        btnRestart = findViewById(R.id.btn_restart);
        btnExit = findViewById(R.id.btn_exit);

        betResult = getIntent().getIntExtra("betResult", 0);
        balance = getIntent().getIntExtra("balance", 0);
        username = getIntent().getStringExtra("name");
        firstPlace = getIntent().getIntExtra("firstPlace", 0);
        secondPlace = getIntent().getIntExtra("secondPlace", 0);
        thirdPlace = getIntent().getIntExtra("thirdPlace", 0);
    }
    private void playSound(int soundResource) {
        // Stop any existing media player before starting a new one
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        mediaPlayer = MediaPlayer.create(this, soundResource);
        mediaPlayer.start();

        // Release the media player after the sound has finished playing
        mediaPlayer.setOnCompletionListener(mp -> {
            mediaPlayer.release();
            mediaPlayer = null;
        });
    }
}