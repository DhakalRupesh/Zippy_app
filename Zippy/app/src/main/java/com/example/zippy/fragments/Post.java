package com.example.zippy.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.zippy.R;
import com.example.zippy.activities.Register_Zippy;
import com.example.zippy.api.Posti;
import com.example.zippy.api.Useri;
import com.example.zippy.model.Advertise;
import com.example.zippy.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Post extends Fragment {

    private EditText etGoodstype, etSendingfrom, etSendingto, etPrice;
    private RadioGroup rdoVehiclegrp;
    private RadioGroup rdoNegotiablegrp;
    private RadioButton rdBtnVehicle;
    private RadioButton rdBtnNegotiable;
    private Button btnPost;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_post, container, false);

            etGoodstype = view.findViewById(R.id.et_post_typeofgoods);
            etSendingfrom = view.findViewById(R.id.et_post_sendingfrom);
            etSendingto = view.findViewById(R.id.et_post_sendlocation);
            etPrice = view.findViewById(R.id.et_post_price);

            rdoVehiclegrp = view.findViewById(R.id.rdo_post_vehiclgrp);
            rdoNegotiablegrp = view.findViewById(R.id.rdo_post_negotiable);

            btnPost = view.findViewById(R.id.btn_post_post);

            btnPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    add_new_post(view);
                }
            });


        return view;
    }

    public void add_new_post(View v){
        String postedBy = "5e322b3aa37cfe35b8fa4105";
        String goods_type = etGoodstype.getText().toString();
        String sending_from = etSendingfrom.getText().toString();
        String sending_to = etSendingto.getText().toString();
        String price =  etPrice.getText().toString();

        int VehicleSetting = rdoVehiclegrp.getCheckedRadioButtonId();
        rdBtnVehicle = v.findViewById(VehicleSetting);
        int Negotiable = rdoNegotiablegrp.getCheckedRadioButtonId();
        rdBtnNegotiable = v.findViewById(Negotiable);

        String vehicle_wanted = rdBtnVehicle.getText().toString();
        String negotiable = rdBtnNegotiable.getText().toString();

        Advertise advertise = new Advertise(postedBy, goods_type, vehicle_wanted, sending_from, sending_to, price, negotiable);
        Posti posti = (Posti) Url.getInstance().create(Useri.class);
        Call<Void> callAdvertise =posti.addAdvertise(advertise);

        callAdvertise.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(), "Your Advertisement is posted successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
