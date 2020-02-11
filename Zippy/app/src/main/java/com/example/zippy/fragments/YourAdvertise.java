package com.example.zippy.fragments;


import android.app.Activity;
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
    Activity activity = getActivity();
    public RecyclerView yPost;
    List<Advertise> yAdvertiselist;
    YourAdvertise_Adapter yourAdvertise_adapter;
    private static final String TAG = "Home";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_your_advertise, container, false);

        yPost = view.findViewById(R.id.your_advertise_recycler);

        GetLoggedPosts();

        return view;
    }

    private void GetLoggedPosts(){
        Posti posti = Url.getInstance().create(Posti.class);
        Call<List<Advertise>> listCallPost = posti.getLoggedAdvertise(Bottom_nav.user.get_id());

        listCallPost.enqueue(new Callback<List<Advertise>>() {
            @Override
            public void onResponse(Call<List<Advertise>> call, Response<List<Advertise>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    return;
                }

                yAdvertiselist = response.body();
                yourAdvertise_adapter = new YourAdvertise_Adapter(getContext(), yAdvertiselist);
                yPost.setAdapter(yourAdvertise_adapter);
                yPost.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<Advertise>> call, Throwable t) {
                Toast.makeText(activity, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailureHome: " + t.getLocalizedMessage());
            }
        });
    }
}
