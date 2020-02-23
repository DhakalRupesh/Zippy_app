package com.example.zippy.fragments;


import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zippy.R;
import com.example.zippy.activities.Bottom_nav;
import com.example.zippy.adapters.YourAdvertise_Adapter;
import com.example.zippy.adapters.YourBooked_Adapter;
import com.example.zippy.api.Posti;
import com.example.zippy.model.Advertise;
import com.example.zippy.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookedAdvertise extends Fragment {

    Activity activity = getActivity();
    public RecyclerView ybPost;
    List<Advertise> ybAdvertiselist;
    YourAdvertise_Adapter yourbAdvertise_adapter;
    private RecyclerView recyclerView;
    private static final String TAG = "Home";
    List<Advertise> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booked_advertise, container, false);

        recyclerView = view.findViewById(R.id.yourbooked_advertise);
        Posti myBooking = Url.getInstance().create(Posti.class);
        Call<List<Advertise>> advertiseCall = myBooking.getBooked(Url.token);

        advertiseCall.enqueue(new Callback<List<Advertise>>() {
            @Override
            public void onResponse(Call<List<Advertise>> call, Response<List<Advertise>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(activity, "No Bookings" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                list = response.body();
                YourBooked_Adapter yourBooked_adapter = new YourBooked_Adapter(getContext(), list);
                recyclerView.setAdapter(yourBooked_adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<Advertise>> call, Throwable t) {
                Toast.makeText(activity, "No Bookings" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });




        return view;
    }

}
