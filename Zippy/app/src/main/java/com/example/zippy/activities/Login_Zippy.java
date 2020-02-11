package com.example.zippy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zippy.R;

import com.example.zippy.bbl.LoginBBL;
import com.example.zippy.strictmode.StrictModeClass;
import com.example.zippy.url.Url;


public class Login_Zippy extends AppCompatActivity implements View.OnClickListener{
    EditText loginEmail, loginpassword;
    TextView go_to_register;
    Button login;
    CheckBox cbRememberme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login__zippy);

        loginEmail = findViewById(R.id.etlogin_Email);
        loginpassword = findViewById(R.id.etlogin_Password);

        login = findViewById(R.id.btnlogin_user);
        go_to_register = findViewById(R.id.tvRegister);

        loginEmail.requestFocus();

        SharedPreferences sharedPreferences = getSharedPreferences( "Zippy", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "empty");
        if (!token.equals("empty")){
            Url.token = token;
            Intent intent = new Intent(Login_Zippy.this, Bottom_nav.class);
            startActivity(intent);
        }

        login.setOnClickListener(this);
        go_to_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnlogin_user:
                Login();
                break;
            case R.id.tvRegister:
                Intent intend_open_register = new Intent(Login_Zippy.this, Register_Zippy.class);
                startActivity(intend_open_register);
                break;
        }
    }

    private void Login() {
        if(CheckEmpty()) {
            String username = loginEmail.getText().toString();
            String password = loginpassword.getText().toString();

            LoginBBL loginBBL = new LoginBBL();
            StrictModeClass.StrictMode();

            if(loginBBL.checkUser(username, password)){
                SharedPreferences sharedPreferences = getSharedPreferences("Zippy",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("token", Url.token);
                editor.commit();
                Intent intent = new Intent(Login_Zippy.this, Bottom_nav.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, "Error!! incorrect username or password", Toast.LENGTH_SHORT).show();
                loginEmail.requestFocus();
            }
        }
    }

    public boolean CheckEmpty(){
        if (loginEmail.getText().toString().trim().isEmpty()) {
            loginEmail.setError("Empty field Email!!");
            return false;
        } if (loginpassword.getText().toString().trim().isEmpty()){
            loginpassword.setError("Empty field password!!");
            return false;
        }
        return true;
    }
}
