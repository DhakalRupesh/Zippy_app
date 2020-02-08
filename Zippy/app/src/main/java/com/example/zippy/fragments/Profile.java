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
import android.widget.Toast;

import com.example.zippy.R;
import com.example.zippy.activities.Bottom_nav;
import com.example.zippy.activities.EditProfile;
import com.example.zippy.activities.Login_Zippy;
import com.example.zippy.activities.Vehicle;
import com.example.zippy.api.Useri;
import com.example.zippy.model.User;
import com.example.zippy.url.Url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends Fragment {
    TextView username, fname, lname, email, phone, description, goTovehicle;
    ImageView vehicleAdd, goToeditProfile, logout, profileImage;
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

        profileImage = view.findViewById(R.id.img_profile_Image);
        goTovehicle = view.findViewById(R.id.tv_profile_vehicle_intent);
        goToeditProfile= view.findViewById(R.id.img_edit_profile);
        logout = view.findViewById(R.id.img_profile_logout);
        
        goTovehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToVehicle = new Intent(getActivity(), Vehicle.class);
                startActivity(goToVehicle);
            }
        });

        goToeditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToEditProfile = new Intent(getActivity(), EditProfile.class);
                startActivity(goToEditProfile);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
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
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String imagePath = Url.imagePath + response.body().getUserimage();
                Picasso.get().load(imagePath).into(profileImage);

                username.setText(Bottom_nav.user.getUsername());
                fname.setText(Bottom_nav.user.getFname());
                lname.setText(Bottom_nav.user.getLname());
                email.setText(Bottom_nav.user.getEmail());
                phone.setText(Bottom_nav.user.getMobile());
                description.setText(Bottom_nav.user.getDescription());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "ofFailure" + t.getLocalizedMessage());
            }
        });
    }

    private void Logout() {
        if(Url.token != "Bearer "){
            Url.token = "Bearer ";
        }
        Intent goToLogin = new Intent(getActivity(), Login_Zippy.class);
        startActivity(goToLogin);
        Toast.makeText(getActivity(), "You are logged out", Toast.LENGTH_SHORT).show();
    }
}
