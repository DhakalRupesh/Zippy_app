package com.example.zippy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zippy.interface_zippy.Useri;
import com.example.zippy.model_zippy.Url;
import com.example.zippy.model_zippy.User;

import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login_Zippy extends AppCompatActivity implements View.OnClickListener{
    EditText loginEmail, loginpassword;
    TextView go_to_register;
    Button login;
    Boolean utype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        hide();
        setContentView(R.layout.activity_login__zippy);

        loginEmail = findViewById(R.id.etlogin_Email);
        loginpassword = findViewById(R.id.etlogin_Password);

        login = findViewById(R.id.btnlogin_user);
        go_to_register = findViewById(R.id.tvRegister);

        login.setOnClickListener(this);
        go_to_register.setOnClickListener(this);

        go_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend_open_register = new Intent(Login_Zippy.this, Register_Zippy.class);
                startActivity(intend_open_register);
            }
        });
    }

//    private void hide(){
//        //hiding the title bar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
//        //making full screen
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        //hiding the navigation bar
////        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//    }

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
                StartLogin();
                break;
            case R.id.tvRegister:
                Intent intend_open_register = new Intent(Login_Zippy.this, Register_Zippy.class);
                startActivity(intend_open_register);
                break;
        }
    }

    private void StartLogin() {
        if(CheckEmpty()){
            GetUtype();

            User login_user =new User(loginEmail.getText().toString(), loginpassword.getText().toString());
            Useri useriApi = Url.getInstance().create(Useri.class);
            Call<Void> callUser = useriApi.login(login_user);

            callUser.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(Login_Zippy.this, "Cannot login!!!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(utype == null) {

                        Intent intendChooseUser = new Intent(Login_Zippy.this, Rider_Dashobard_zippy.class);
                        startActivity(intendChooseUser);

                    } else if(utype == true){
                        Intent intentOpenDashboard = new Intent(Login_Zippy.this, MainActivity.class);
                        startActivity(intentOpenDashboard);
                    } else if(utype == false) {
                        Intent intentOpenDashboard1 = new Intent(Login_Zippy.this, Register_Zippy.class);
                        startActivity(intentOpenDashboard1);
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(Login_Zippy.this, "Error!! login failed" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void GetUtype(){
        Useri useriUtype = Url.getInstance().create(Useri.class);
        Call<User> usercallUtype = useriUtype.getme();

        usercallUtype.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Login_Zippy.this, "Error!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = response.body();
//                utype = user.getUtype();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Login_Zippy.this, "cant get user type", Toast.LENGTH_SHORT).show();
            }
        });
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
