package com.example.racingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.result_view);
        Button btnRestart = findViewById(R.id.btn_restart);
        Button btnExit = findViewById(R.id.btn_exit);

        int betResult = getIntent().getIntExtra("betResult", 0);
        int firstPlace = getIntent().getIntExtra("firstPlace", 0);
        int secondPlace = getIntent().getIntExtra("secondPlace", 0);
        int thirdPlace = getIntent().getIntExtra("thirdPlace", 0);

        // Set tên và hình ảnh của ngựa về nhất
        TextView tv1stPlace = findViewById(R.id.tv1stPlace);
        tv1stPlace.setText("Horse " + firstPlace); // Display horse number for 1st place
        tv1stPlace.setBackgroundResource(getHorseImage(firstPlace)); // Set corresponding horse image

        // Set tên và hình ảnh của ngựa về nhì
        TextView tv2ndPlace = findViewById(R.id.tv_2nd_place);
        tv2ndPlace.setText("Horse " + secondPlace); // Display horse number for 2nd place
        tv2ndPlace.setBackgroundResource(getHorseImage(secondPlace)); // Set corresponding horse image

        // Set tên và hình ảnh của ngựa về ba
        TextView tv3rdPlace = findViewById(R.id.tv_3rd_place);
        tv3rdPlace.setText("Horse " + thirdPlace); // Display horse number for 3rd place
        tv3rdPlace.setBackgroundResource(getHorseImage(thirdPlace));

        // Hiển thị kết quả cá cược
        TextView resultTextView = findViewById(R.id.result_text);
        resultTextView.setText(betResult > 0 ? "YOU WIN!" : "YOU LOSE!");

      btnRestart.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(MainActivity.this, BettingActivity.class);
              //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
              startActivity(intent);
              finish();
          }
      });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move the task to the back
                moveTaskToBack(true);
            }
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
}