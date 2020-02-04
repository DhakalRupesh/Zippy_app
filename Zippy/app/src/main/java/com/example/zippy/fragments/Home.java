package com.example.zippy.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

    public RecyclerView featuredpost;
    public List<Advertise> advertiseList;
    private static final String TAG = "Home";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        featuredpost = view.findViewById(R.id.rv_delevery_advertisement);

//        advertiseList = new ArrayList<>();
////
//        advertiseList.add(new Advertise("me", "firniture", "4wheeler", "kathmandu", "bhaktapur", "2000" , "yes", true,""));
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        Advertise_Adapter adapter = new Advertise_Adapter(advertiseList);
//        featuredpost.setAdapter(adapter);
//        featuredpost.setLayoutManager(layoutManager);

        GetAllPosts();

        return view;
    }

    private void GetAllPosts(){

            Posti postapi = Url.getInstance().create(Posti.class);
            Call<List<Advertise>> listCall = postapi.getAdvertise();

            listCall.enqueue(new Callback<List<Advertise>>() {
                @Override
                public void onResponse(Call<List<Advertise>> call, Response<List<Advertise>> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<Advertise> list = response.body();
                    Advertise_Adapter advertise_adapter = new Advertise_Adapter(list);
                    featuredpost.setLayoutManager(new LinearLayoutManager(getContext()));
                    featuredpost.setAdapter(advertise_adapter);
                }

                @Override
                public void onFailure(Call<List<Advertise>> call, Throwable t) {
                    Log.e(TAG, "onFailureHome: " + t.getLocalizedMessage());
                }
            });

    }
}
