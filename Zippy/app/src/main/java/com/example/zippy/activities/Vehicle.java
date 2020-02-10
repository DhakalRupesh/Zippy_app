package com.example.zippy.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.zippy.R;
import com.example.zippy.api.Vehiclei;
import com.example.zippy.model.Advertise;
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

public class Vehicle extends AppCompatActivity {

    private EditText BrandName, Vehicle_no, License_no;
    private RadioGroup rdoVehicleTypegrp;
    private RadioButton rdBtnVehicleType;
    private ImageView imgLicense;
    String imagePath;
    private String imageName;
    private Button btnVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        BrandName = findViewById(R.id.et_vehicle_brand_name);
        Vehicle_no = findViewById(R.id.et_vehicle_vehicle_no);
        License_no = findViewById(R.id.et_vehicle_license_no);

        rdoVehicleTypegrp = findViewById(R.id.rdo_vehicle_vehiclgrp);

        imgLicense = findViewById(R.id.img_vehicle_license);

        btnVerify = findViewById(R.id.btn_vehicle_post);

        imgLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(CheckEmpty()) {
                    saveImageOnly();
                    PostVehicleInfo();
//                }
            }
        });

    }

    private void PostVehicleInfo(){
        String VehicleAddedBy = Bottom_nav.user.get_id();
        String brandName = BrandName.getText().toString();

        String vehicle_no = Vehicle_no.getText().toString();
        String license_no = License_no.getText().toString();

        int VehicleSetting = rdoVehicleTypegrp.getCheckedRadioButtonId();
        rdBtnVehicleType = findViewById(VehicleSetting);

        String vehicleType = rdBtnVehicleType.getText().toString();

        Vehicles vehicles = new Vehicles( brandName, vehicleType, vehicle_no, license_no, imageName);
        Vehiclei vehicleAPI = Url.getInstance().create(Vehiclei.class);
        Call<Void> voidCallVehicle = vehicleAPI.addVehicle(vehicles);

        voidCallVehicle.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(Vehicle.this, "Vehicle detail sent for verification", Toast.LENGTH_LONG).show();
                ClearField();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Vehicle.this, "ErrorVehicle" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(Vehicle.this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imgLicense.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri){
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(Vehicle.this, uri, projection, null, null, null);
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
            Toast.makeText(Vehicle.this, "Image Inserted", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(Vehicle.this, "Error", Toast.LENGTH_SHORT).show();
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
