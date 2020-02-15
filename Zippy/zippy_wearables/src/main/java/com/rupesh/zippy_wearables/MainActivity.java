package com.rupesh.zippy_wearables;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rupesh.zippy_wearables.Url.Url;
import com.rupesh.zippy_wearables.bbl.LoginBBL;
import com.rupesh.zippy_wearables.strictmode.StrictModeClass;

public class MainActivity extends WearableActivity {

    EditText loginEmail, loginpassword;
    TextView go_to_register;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEmail = findViewById(R.id.et_username_ware);
        loginpassword = findViewById(R.id.et_password_ware);

        login = findViewById(R.id.btnLogin_ware);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        // Enables Always-on
        setAmbientEnabled();
    }
    private void Login() {
        if(CheckEmpty()) {
            String username = "tonystark";
            String password = "tony5";

            if(loginEmail.equals(username) && password.equals(password)){
                Intent intent = new Intent(this, Dashboard.class);
                startActivity(intent);

            }else {
                Toast.makeText(this, "Error!! incorrect username or password", Toast.LENGTH_SHORT).show();
                loginEmail.requestFocus();
            }

//            LoginBBL loginBBL = new LoginBBL(username, password);
//            StrictModeClass.StrictMode();
//
//            if(loginBBL.checkUser(username, password)){
//                String token = Url.token;
//                Intent intent = new Intent(this, Dashboard.class);
//                intent.putExtra("token", token);
//                startActivity(intent);
//                finish();
//            }else {
//                Toast.makeText(this, "Error!! incorrect username or password", Toast.LENGTH_SHORT).show();
//                loginEmail.requestFocus();
//            }
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
