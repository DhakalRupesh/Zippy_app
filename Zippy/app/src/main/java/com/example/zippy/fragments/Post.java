package com.example.zippy.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.zippy.R;

public class Post extends Fragment {

    private EditText etGoodstype, etSendingfrom, etSendingto, etPrice;
    private RadioGroup rdoVehiclegrp;
    private RadioGroup rdoNegotiablegrp;
    private RadioButton rdBtnVehicle;
    private RadioButton rdBtnNegotiable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);

            etGoodstype = view.findViewById(R.id.et_post_typeofgoods);
            etSendingfrom = view.findViewById(R.id.et_post_sendingfrom);
            etSendingto = view.findViewById(R.id.et_post_sendlocation);
            etPrice = view.findViewById(R.id.et_post_price);

            rdoVehiclegrp = view.findViewById(R.id.rdo_post_vehicle_need);
            rdoNegotiablegrp = view.findViewById(R.id.rdo_post_negotiable);



        return view;
    }

    public void add_new_post(){
        String goods_type = etGoodstype.getText().toString();
        String sending_from = etSendingfrom.getText().toString();
        String sending_to = etSendingto.getText().toString();
        String price =  etPrice.getText().toString();

        String vehicle = rdBtnVehicle.


    }

}
