package com.example.racingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int betResult, balance, firstPlace, secondPlace, thirdPlace;
    String username;
    Button btnRestart, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.result_view);

        Projecting();

        // Set tên và hình ảnh của ngựa về nhất
        TextView tv1stPlace = findViewById(R.id.tv1stPlace);
        tv1stPlace.setText("Horse " + firstPlace); // Display horse number for 1st place
        tv1stPlace.setBackgroundResource(getHorseImage(firstPlace)); // Set corresponding horse image
        // Set tên và hình ảnh của ngựa về nhì
        TextView tv2ndPlace = findViewById(R.id.tv_2nd_place);
        tv2ndPlace.setText("Horse " + secondPlace);
        tv2ndPlace.setBackgroundResource(getHorseImage(secondPlace));
        // Set tên và hình ảnh của ngựa về ba
        TextView tv3rdPlace = findViewById(R.id.tv_3rd_place);
        tv3rdPlace.setText("Horse " + thirdPlace);
        tv3rdPlace.setBackgroundResource(getHorseImage(thirdPlace));

        // Hiển thị kết quả cá cược
        TextView resultTextView = findViewById(R.id.result_text);
        resultTextView.setText(betResult > 0 ? "YOU WIN "+betResult+"!" : "YOU LOSE "+betResult+"!");
        balance += betResult;
        // Hiển thị số dư hiện tại
        TextView currentBalance = findViewById(R.id.current_balance);
        currentBalance.setText("Balance: "+balance);

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
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move the task to the back
                MainActivity.this.moveTaskToBack(true);
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
}