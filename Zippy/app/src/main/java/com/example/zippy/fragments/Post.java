package com.example.zippy.fragments;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.zippy.R;
import com.example.zippy.activities.Bottom_nav;
import com.example.zippy.activities.Register_Zippy;
import com.example.zippy.api.Posti;
import com.example.zippy.api.Useri;
import com.example.zippy.api.Vehiclei;
import com.example.zippy.model.Advertise;
import com.example.zippy.model.User;
import com.example.zippy.serverresponse.ImageResponse;
import com.example.zippy.strictmode.StrictModeClass;
import com.example.zippy.url.Url;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Post extends Fragment {

    private EditText etGoodstype, etSendingfrom, etSendingto, etPrice;
    private RadioGroup rdoVehiclegrp;
    private RadioGroup rdoNegotiablegrp;
    private RadioButton rdBtnVehicle;
    private RadioButton rdBtnNegotiable;
    private ImageView imgPost;
    String imagePath;
    private String imageName;
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

            imgPost = view.findViewById(R.id.img_post_post);

            btnPost = view.findViewById(R.id.btn_post_post);

            etGoodstype.requestFocus();

            imgPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BrowseImage();
                }
            });

            btnPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(CheckEmpty()) {
                        AddNewPost(view);
                        saveImageOnly();
                    }
                }
            });


        return view;
    }

//    private void BrowseImage() {
//        Intent browseImage = new Intent(Intent.ACTION_PICK);
//        browseImage.setType("image/*");
//        startActivityForResult(browseImage, 0);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == Activity.RESULT_OK) {
//            if (data == null) {
//                Toast.makeText(getActivity(), "Please select an image ", Toast.LENGTH_SHORT).show();
//            }
//        }
//        Uri uri = data.getData();
//        imgPost.setImageURI(uri);
//        imagePath = getRealPathFromUri(uri);
//    }
//
//    private String getRealPathFromUri(Uri uri){
//        String[] projection = {MediaStore.Images.Media.DATA};
//        CursorLoader loader = new CursorLoader(getActivity().getApplicationContext(),
//                uri, projection, null, null, null);
//        Cursor cursor = loader.loadInBackground();
//        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String result = cursor.getString(colIndex);
//        cursor.close();
//        return result;
//    }
//
//    private void SaveImageOnly(){
//        File file = new File(imagePath);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
//                file.getName(), requestBody);
//
//        Posti posti = Url.getInstance().create(Posti.class);
//        Call<ImageResponse> imageResponseCall = posti.uploadImage(body);
//        StrictModeClass.StrictMode();
//
//        try {
//            Response<ImageResponse> imageResponseResponse = imageResponseCall.execute();
//            imageName = imageResponseResponse.body().getFilename();
//            Toast.makeText(getActivity(), "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            Toast.makeText(getActivity(), "Image Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//
//    }

    public void AddNewPost(View v){
        String postedBy = Bottom_nav.user.get_id();
        String goods_type = etGoodstype.getText().toString();
        String sending_from = etSendingfrom.getText().toString();
        String sending_to = etSendingto.getText().toString();
        String price = etPrice.getText().toString();

        int VehicleSetting = rdoVehiclegrp.getCheckedRadioButtonId();
        rdBtnVehicle = v.findViewById(VehicleSetting);
        int Negotiable = rdoNegotiablegrp.getCheckedRadioButtonId();
        rdBtnNegotiable = v.findViewById(Negotiable);

        String vehicle_wanted = rdBtnVehicle.getText().toString();
        String negotiable = rdBtnNegotiable.getText().toString();

        Advertise advertise = new Advertise(postedBy, goods_type, vehicle_wanted, sending_from, sending_to, price, negotiable, imageName);

        Posti posti = Url.getInstance().create(Posti.class);
        Call<Void> callAdvertise = posti.addAdvertise(advertise);

        callAdvertise.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(), "Your Advertisement is posted successfully", Toast.LENGTH_SHORT).show();
                ClearField();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Activity.RESULT_OK) {
            if (data == null) {
                Toast.makeText(getActivity(), "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imgPost.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri){
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getContext(), uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    public void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile", file.getName(), requestBody);

        Posti posti = Url.getInstance().create(Posti.class);
        Call<ImageResponse> responseBodyCall = posti.uploadPostImage(body);

        StrictModeClass.StrictMode();

        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(getContext(), "Image Inserted", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public Boolean CheckEmpty(){
        if (etGoodstype.getText().toString().trim().isEmpty()){
            etGoodstype.setError("Empty field !!");
            return false;
        }
        if (etSendingfrom.getText().toString().trim().isEmpty()){
            etSendingfrom.setError("Empty field !!");
            return false;
        }
        if (etSendingto.getText().toString().trim().isEmpty()){
            etSendingto.setError("Empty field !!");
            return false;
        }if (etPrice.getText().toString().trim().isEmpty()){
            etPrice.setError("Empty field !!");
            return false;
        }
        return true;
    }

    public void ClearField(){
        etGoodstype.setText("");
        etSendingfrom.setText("");
        etSendingto.setText("");
        etPrice.setText("");
    }
}
