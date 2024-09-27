package com.example.racingapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUserName;
    private EditText etPassword;
    private Button btnSignin;
    private  final String REQUIRE ="Require";

    private List<User> userList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edtUserName =findViewById(R.id.username);
        etPassword =findViewById(R.id.password);
        //NotAccountyet =findViewById(R.id.NotAccountyet);
        btnSignin =findViewById(R.id.SignInLoginPage);
     btnSignin.setOnClickListener(this);



        userList.add(new User("user1", "password1", 100,"Quang"));
        userList.add(new User("user2", "password2", 200,"Quang2"));
        userList.add(new User("user3", "password3", 300,"Quang3"));



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.LoginPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
            BettingForm(); // Call your sign-in method here


    }
    private  boolean checkInput (){
        if (TextUtils.isEmpty(edtUserName.getText().toString())){
            edtUserName.setError(REQUIRE);
            return false;

        }
        if(TextUtils.isEmpty(etPassword.getText().toString())){
            etPassword.setError(REQUIRE);
            return false;
        }
        return  true;


    }
    private User validateUser(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; // Return the valid user
            }
        }
        return null; // Invalid login
    }

    private void BettingForm() {
        if(!checkInput()){
            return;
        }
        User loggedInUser = validateUser(edtUserName.getText().toString(), etPassword.getText().toString());
        if (loggedInUser != null) {
            Intent intent = new Intent(this, BettingActivity.class);
            intent.putExtra("name", loggedInUser.getName());
            intent.putExtra("balance", loggedInUser.getBalance());
            startActivity(intent);
        } else {
            // Show error message
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
      /*  Intent intent = new Intent(this,BettingActivity.class);
        startActivity(intent);
        finish();
*/
    }
}