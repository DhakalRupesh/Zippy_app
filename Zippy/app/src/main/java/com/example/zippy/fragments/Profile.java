package com.example.zippy.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zippy.R;
import com.example.zippy.api.Useri;
import com.example.zippy.model.User;
import com.example.zippy.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends Fragment {
    TextView username, fname, lname, email, phone, description, goTovehicle;
    ImageView vehicleAdd;
    public static final String TAG = "profile";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        username = view.findViewById(R.id.tv_profile_uname);
        fname = view.findViewById(R.id.tv_profile_fname);
        lname = view.findViewById(R.id.tv_profile_lname);
        email = view.findViewById(R.id.tv_profile_email);
        phone = view.findViewById(R.id.tv_profile_mobile);
        description = view.findViewById(R.id.tv_profile_desc);
//        userImage = view.findViewById(R.id.img_profile_Image);

        goTovehicle = view.findViewById(R.id.tv_profile_vehicle_intent);
        
        goTovehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        loadUser();

        return view;
    }

    private void loadUser() {
        Useri useri = Url.getInstance().create(Useri.class);
        Call<User> userCall = useri.getme(Url.token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                username.setText(user.getUsername());
                fname.setText(user.getFname());
                lname.setText(user.getLname());
                email.setText(user.getEmail());
                phone.setText(user.getMobile());
                description.setText(user.getDescription());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "ofFailure" + t.getLocalizedMessage());
            }
        });
    }
}
