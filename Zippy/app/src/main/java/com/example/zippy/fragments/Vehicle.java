package com.example.zippy.fragments;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

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
import com.example.zippy.api.Vehiclei;
import com.example.zippy.model.Vehicles;
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

public class Vehicle extends Fragment {

    private EditText BrandName, Vehicle_no, License_no;
    private RadioGroup rdoVehicleTypegrp;
    private RadioButton rdBtnVehicleType;
    private ImageView imgLicense;
    String imagePath;
    private String imageName = "";
    private Button btnVerify;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_vehicle, container, false);

        BrandName = view.findViewById(R.id.et_vehicle_brand_name);
        Vehicle_no = view.findViewById(R.id.et_vehicle_vehicle_no);
        License_no = view.findViewById(R.id.et_vehicle_license_no);

        rdoVehicleTypegrp = view.findViewById(R.id.rdo_vehicle_vehiclgrp);

        imgLicense = view.findViewById(R.id.img_vehicle_license);

        btnVerify = view.findViewById(R.id.btn_vehicle_post);

        imgLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckEmpty()) {
                    if(LicenseNoValidate()){
                        PostVehicleInfo(v);
                        saveImageOnly();
                    }
                }
            }
        });

        return view;
    }

    private void PostVehicleInfo(View v){
        String VehicleAddedBy = Url.token;
        String brandName = BrandName.getText().toString();

        String vehicle_no = Vehicle_no.getText().toString();
        String license_no = License_no.getText().toString();

        int VehicleSetting = rdoVehicleTypegrp.getCheckedRadioButtonId();
        rdBtnVehicleType = v.findViewById(VehicleSetting);

        String vehicleType = rdBtnVehicleType.getText().toString();

        Vehicles vehicles = new Vehicles(VehicleAddedBy, brandName, vehicleType, vehicle_no, license_no, imageName);
        Vehiclei vehicleAPI = Url.getInstance().create(Vehiclei.class);
        Call<Void> voidCallVehicle = vehicleAPI.addVehicle(vehicles);

        voidCallVehicle.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(), "Vehicle detail sent for verification", Toast.LENGTH_LONG).show();
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
        imgLicense.setImageURI(uri);
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

        Vehiclei vehicleAPI = Url.getInstance().create(Vehiclei.class);
        Call<ImageResponse> responseBodyCall = vehicleAPI.uploadLicenseImage(body);

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

    private boolean LicenseNoValidate(){
        boolean status=true;
        if (License_no.getText().toString().length() > 12 || License_no.getText().toString().length() < 12) {
            License_no.setError("Invalid License Number");
            status=false;
        }
        return status;
    }

    private boolean CheckEmpty(){
        if (BrandName.getText().toString().trim().isEmpty()){
            BrandName.setError("Empty field !!");
            return false;
        }
        if (Vehicle_no.getText().toString().trim().isEmpty()){
            Vehicle_no.setError("Empty field !!");
            return false;
        }
        if (License_no.getText().toString().trim().isEmpty()) {
            License_no.setError("Empty field !!");
            return false;
        }
        return true;
    }

    private void ClearField(){
        BrandName.setText("");
        Vehicle_no.setText("");
        License_no.setText("");
    }

}
