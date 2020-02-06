package com.example.zippy.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
    List<Advertise> adlist;
    Advertise_Adapter advertise_adapter;
    private static final String TAG = "Home";
    private EditText btnSearchLocation;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        featuredpost = view.findViewById(R.id.rv_delevery_advertisement);
        btnSearchLocation = view.findViewById(R.id.et_home_search);

//        adlist = new ArrayList<>();
////
//        adlist.add(new Advertise("me", "furniture", "4wheeler", "kathmandu", "bhaktapur", "2000" , "yes", "",true));
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        Advertise_Adapter adapter = new Advertise_Adapter(adlist);
//        featuredpost.setAdapter(adapter);
//        featuredpost.setLayoutManager(layoutManager);

        GetAllPosts();

        btnSearchLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

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

                adlist = response.body();
                advertise_adapter = new Advertise_Adapter(adlist);
                featuredpost.setLayoutManager(new LinearLayoutManager(getContext()));
                featuredpost.setAdapter(advertise_adapter);
            }

            @Override
            public void onFailure(Call<List<Advertise>> call, Throwable t) {
                Log.e(TAG, "onFailureHome: " + t.getLocalizedMessage());
            }
        });
    }

    // search
    private void filter(String text) {
        ArrayList<Advertise> filteredList=new ArrayList<>();
        for( Advertise item: adlist){
            if( item.getDestinationofdelivery().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        advertise_adapter.FilterPlaces(filteredList);
    }

    private void UpdateStatus() {

    }
}
