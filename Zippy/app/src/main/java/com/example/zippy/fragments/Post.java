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
    private RadioButton rdoVehicle;
    private RadioGroup rdoNegociablegrp;
    private RadioButton rdoNegociable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);

            etGoodstype = view.findViewById(R.id.et_post_typeofgoods);
            etGoodstype = view.findViewById(R.id.et_post_typeofgoods);
            etGoodstype = view.findViewById(R.id.et_post_typeofgoods);
            etGoodstype = view.findViewById(R.id.et_post_typeofgoods);

            rdoVehiclegrp = view.findViewById()

        return view;
    }


}
