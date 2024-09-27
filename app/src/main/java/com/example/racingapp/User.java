package com.example.racingapp;

public class User {
    public User(String username, String password, double balance) {
        this.username = username;
        Password = password;
        this.balance = balance;
    }

    private String username;
    private String Password;
    private double balance;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
