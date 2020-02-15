package com.example.zippy.fragments;


import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zippy.R;
import com.example.zippy.activities.Bottom_nav;
import com.example.zippy.adapters.YourAdvertise_Adapter;
import com.example.zippy.adapters.YourBooked_Adapter;
import com.example.zippy.model.Advertise;

import java.util.List;


public class BookedAdvertise extends Fragment {

    Activity activity = getActivity();
    public RecyclerView ybPost;
    List<Advertise> ybAdvertiselist;
    YourAdvertise_Adapter yourbAdvertise_adapter;
    private RecyclerView recyclerView;
    private static final String TAG = "Home";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booked_advertise, container, false);

        recyclerView = view.findViewById(R.id.yourbooked_advertise);

        YourBooked_Adapter yourBooked_adapter = new YourBooked_Adapter(getContext(), Bottom_nav.advertiselist);
        recyclerView.setAdapter(yourBooked_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }

}
