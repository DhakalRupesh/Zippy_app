package com.example.zippy.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zippy.R;
import com.example.zippy.activities.Bottom_nav;
import com.example.zippy.adapters.Advertise_Adapter;
import com.example.zippy.adapters.YourAdvertise_Adapter;
import com.example.zippy.api.Posti;
import com.example.zippy.model.Advertise;
import com.example.zippy.url.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YourAdvertise extends Fragment {

    public RecyclerView Yourpost;
    List<Advertise> adlist;
    Advertise_Adapter advertise_adapter;
    private static final String TAG = "Home";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_your_advertise, container, false);

        return view;
    }

    private void GetAllPosts(){

        Posti postapi = Url.getInstance().create(Posti.class);
        Call<List<Advertise>> listCall = postapi.getYourAdvertise("5e3a6c7cc2f17b24c4fba382");

        listCall.enqueue(new Callback<List<Advertise>>() {
            @Override
            public void onResponse(Call<List<Advertise>> call, Response<List<Advertise>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                adlist = response.body();

            }

            @Override
            public void onFailure(Call<List<Advertise>> call, Throwable t) {
                Log.e(TAG, "onFailureHome: " + t.getLocalizedMessage());
            }
        });
    }

}
