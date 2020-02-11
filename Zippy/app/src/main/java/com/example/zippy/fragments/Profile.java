package com.example.zippy.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import static android.content.Context.MODE_PRIVATE;

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

        loadUser(view);
        return view;
    }

    private void loadUser(final View view) {
        Useri useri = Url.getInstance().create(Useri.class);
        Call<User> userCall = useri.getme(Url.token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = response.body();
                String imgPath = Url.imagePath +  response.body().getUserimage();

                ImageView profile = view.findViewById(R.id.img_profile_Image);

                try{
                    Picasso.get().load(imgPath).into(profile);

                }catch (Exception e){
                    Picasso.get().load(R.drawable.user1).into(profile);
                }
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

    private void Logout() {
        if(Url.token != "Bearer "){
            Url.token = "Bearer ";
        }
        Intent goToLogin = new Intent(getActivity(), Login_Zippy.class);
        startActivity(goToLogin);
        Toast.makeText(getActivity(), "You are logged out", Toast.LENGTH_SHORT).show();
    }

    private void spLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Zippy",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.remove("token");
                editor.commit();
                Url.token = "Bearer ";
                Intent redirect_To_login = new Intent(getActivity(), Login_Zippy.class);
                startActivity(redirect_To_login);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
