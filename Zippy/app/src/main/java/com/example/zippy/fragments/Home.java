package com.example.zippy.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zippy.R;
import com.example.zippy.adapters.Advertise_Adapter;
import com.example.zippy.api.Posti;
import com.example.zippy.model.Advertise;
import com.example.zippy.url.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment {

    private RecyclerView featuredpost;
    public List<Advertise> advertiseList;
    private static final String TAG = "Home";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
            featuredpost = view.findViewById(R.id.rv_delevery_advertisement);
//            advertiseList = new ArrayList<>();
////
////            advertiseList.add(new Advertise("me", "firniture", "4wheeler", "kathmandu", "bhaktapur", 2000 , "yes", true, R.drawable.jori_sophia));
////
//            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//            Advertise_Adapter adapter = new Advertise_Adapter(advertiseList, getContext());
//            featuredpost.setAdapter(adapter);
//            featuredpost.setLayoutManager(layoutManager);
            GetAllPosts();

        return view;
    }

    private void GetAllPosts(){
        try {
            Posti posti = Url.getInstance().create(Posti.class);
            Call<List<Advertise>> listCallAdvertise = posti.getAdvertise();

            listCallAdvertise.enqueue(new Callback<List<Advertise>>() {
                @Override
                public void onResponse(Call<List<Advertise>> call, Response<List<Advertise>> response) {
                    List<Advertise> advertisesList = response.body();
                    Advertise_Adapter advertise_adapter = new Advertise_Adapter(advertisesList);
                    featuredpost.setLayoutManager(new LinearLayoutManager(getContext()));
                    featuredpost.setAdapter(advertise_adapter);
                }

                @Override
                public void onFailure(Call<List<Advertise>> call, Throwable t) {
                    Log.e(TAG, "fail: " + t.getLocalizedMessage());
                }
            });

        } catch (Exception e) {

        }
    }
}
