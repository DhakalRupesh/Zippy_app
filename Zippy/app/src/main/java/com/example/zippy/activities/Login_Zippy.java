package com.example.zippy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zippy.R;
import com.example.zippy.api.Useri;
import com.example.zippy.bbl.LoginBBL;
import com.example.zippy.strictmode.StrictModeClass;
import com.example.zippy.url.Url;
import com.example.zippy.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Zippy extends AppCompatActivity implements View.OnClickListener{
    EditText loginEmail, loginpassword;
    TextView go_to_register;
    Button login;
    Boolean utype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login__zippy);

        loginEmail = findViewById(R.id.etlogin_Email);
        loginpassword = findViewById(R.id.etlogin_Password);

        login = findViewById(R.id.btnlogin_user);
        go_to_register = findViewById(R.id.tvRegister);

        login.setOnClickListener(this);
        go_to_register.setOnClickListener(this);

    }

//    private void Load_Users(){
//        Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl(Url.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();
//
//        Useri useri = retrofit.create(Useri.class);
//
//        Call<List<User>> userCall = useri.getuser();
//
//        userCall.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//                Toast.makeText(this, "Error ");
//            }
//        });
//
//    }

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
                String token = Url.token;
                Intent intent = new Intent(Login_Zippy.this, Bottom_nav.class);
                intent.putExtra("token", token);
                startActivity(intent);
                finish();
            }
            Toast.makeText(this, "Error!! incorrect username or password", Toast.LENGTH_SHORT).show();
            loginEmail.requestFocus();
        }
    }

//    public void GetUtype(){
//        Useri useriUtype = Url.getInstance().create(Useri.class);
//        Call<User> usercallUtype = useriUtype.getme();
//
//        usercallUtype.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if(!response.isSuccessful()){
//                    Toast.makeText(Login_Zippy.this, "Error!!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                User user = response.body();
////                utype = user.getUtype();
//
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Toast.makeText(Login_Zippy.this, "cant get user type", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

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
