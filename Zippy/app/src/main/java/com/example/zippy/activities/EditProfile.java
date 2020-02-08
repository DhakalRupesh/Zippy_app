package com.example.zippy.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zippy.R;
import com.example.zippy.api.Useri;
import com.example.zippy.model.User;
import com.example.zippy.serverresponse.ImageResponse;
import com.example.zippy.strictmode.StrictModeClass;
import com.example.zippy.url.Url;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {
    private EditText fname, lname, phone, username, email, description;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        fname = findViewById(R.id.et_pro_fname);
        lname = findViewById(R.id.et_pro_lname);
        phone = findViewById(R.id.et_pro_mobile);
        username = findViewById(R.id.et_pro_uname);
        email = findViewById(R.id.et_pro_email);
        description = findViewById(R.id.et_pro_description);

        btnUpdate = findViewById(R.id.btn_pro_update);

        getUser();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckEmpty()){
                    UpdateUserInfo();
                }
            }
        });
    }

    private void getUser(){
        fname.setText(Bottom_nav.user.getFname());
        lname.setText(Bottom_nav.user.getLname());
        phone.setText(Bottom_nav.user.getMobile());
        username.setText(Bottom_nav.user.getUsername());
        email.setText(Bottom_nav.user.getEmail());
        description.setText(Bottom_nav.user.getDescription());
    }

    private void UpdateUserInfo(){
        String updatePhone = phone.getText().toString();
        String updateUsername = username.getText().toString();
        String updateEmail = email.getText().toString();
        String updateDesc = description.getText().toString();

        User user = new User(updatePhone, updateEmail, updateUsername, updateDesc);

        Useri useri = Url.getInstance().create(Useri.class);
        Call<User> userCall = useri.updateProfile(Url.token, user);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(EditProfile.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                getUser();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(EditProfile.this, "Error!! " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean CheckEmpty(){
        if (phone.getText().toString().trim().isEmpty()){
            phone.setError("Empty field !!");
            return false;
        }
        if (username.getText().toString().trim().isEmpty()){
            username.setError("Empty field !!");
            return false;
        }
        if (email.getText().toString().trim().isEmpty()){
            email.setError("Empty field !!");
            return false;
        }
        return true;
    }
}
