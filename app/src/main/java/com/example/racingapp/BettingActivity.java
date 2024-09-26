package com.example.racingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class BettingActivity extends AppCompatActivity {


    private final HashMap<Integer, Integer> selectedHorse = new HashMap<>();
    private CheckBox horse1, horse2, horse3;
    private EditText betAmount1, betAmount2, betAmount3;
    private TextView balanceText, userName;
    private Button startButton;
    private int balance;
    private Intent intent;
    private String username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.betting_view);
        Projecting();

        startButton.setOnClickListener(v -> {
            betHorse();
            CheckBalance();
        });
    }

    private void Projecting() {
        intent = new Intent(this, RacingActivity.class);
        horse1 = findViewById(R.id.horse1);
        horse2 = findViewById(R.id.horse2);
        horse3 = findViewById(R.id.horse3);
        balanceText = findViewById(R.id.balance);
        userName = findViewById(R.id.userName);
        betAmount1 = findViewById(R.id.betAmount1);
        betAmount2 = findViewById(R.id.betAmount2);
        betAmount3 = findViewById(R.id.betAmount3);
        startButton = findViewById(R.id.startRaceButton);
        // Get the balance and username from the intent
        // If the intent is null, set the balance to 1000 and the username to "user"
        balance = intent.getIntExtra("balance", 1000);
        username = intent.getStringExtra("username");
        balanceText.setText(getString(R.string.balance, balance));
        if (username == null) {
            username = "user";
        }
        userName.setText(username);
    }

    private void CheckBalance() {
        if (balance <= 0) {
            showErrorDialog("Error", "You don't have enough balance to place a bet");
            return;
        }

        int totalBet = selectedHorse
                .values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        if (totalBet > balance) {
            showErrorDialog("Error", "You don't have enough balance to place this bet");
            return;
        }
        MoveToRace();
    }

    private void showErrorDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void chooseHorse(int horse, int amount) {
        if (horse == 1) {
            selectedHorse.put(1, amount);
        } else if (horse == 2) {
            selectedHorse.put(2, amount);
        } else if (horse == 3) {
            selectedHorse.put(3, amount);
        }
    }

    private void betHorse() {
        if (horse1.isChecked()) {
            chooseHorse(1, parseInt(betAmount1.getText().toString()));
        }
        if (horse2.isChecked()) {
            chooseHorse(2, parseInt(betAmount2.getText().toString()));
        }
        if (horse3.isChecked()) {
            chooseHorse(3, parseInt(betAmount3.getText().toString()));
        }
    }

    private void MoveToRace() {
        intent.putExtra("balance", balance);
        intent.putExtra("selectedHorse", selectedHorse);
        startActivity(intent);
    }

    private int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
