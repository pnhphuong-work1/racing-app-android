package com.example.racingapp;

public class User {


    public User(String username, String password, int balance, String name) {
        this.username = username;
        Password = password;
        this.balance = balance;
        this.name =name;
    }

    private String username;
    private String Password;
    private int balance;
    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
