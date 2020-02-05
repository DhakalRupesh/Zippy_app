package com.example.zippy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zippy.R;
import com.example.zippy.api.Useri;
import com.example.zippy.url.Url;
import com.example.zippy.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register_Zippy extends AppCompatActivity {
    private EditText et_fname, et_lname, et_email, et_phone, et_username, et_Password;
    private Button btn_register;
    private TextView tv_go_to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__zippy);

        et_fname = findViewById(R.id.et_fname);
        et_lname = findViewById(R.id.et_lname);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
        et_username = findViewById(R.id.et_uname);
        et_Password = findViewById(R.id.et_password);

        btn_register = findViewById(R.id.btn_register);

        tv_go_to_login = findViewById(R.id.tv_go_to_login);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register_user();
            }
        });

        tv_go_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLogin = new Intent(getApplicationContext(), Login_Zippy.class);
                startActivity(goToLogin);
            }
        });
    }

    private void Register_user(){
        String fname = et_fname.getText().toString();
        String lname = et_lname.getText().toString();
        String phone = et_phone.getText().toString();
        String email = et_email.getText().toString();
        String uname = et_username.getText().toString();
        String paswd = et_Password.getText().toString();

        User user = new User(fname, lname, phone, email, uname, paswd);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Useri useri = retrofit.create(Useri.class);
        Call<Void> userCall = useri.addUser(user);

        System.out.println("Retrofit call is: "+userCall);

        userCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Register_Zippy.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(Register_Zippy.this, "Register successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("The error is: "+t.getLocalizedMessage());
                Toast.makeText(Register_Zippy.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
