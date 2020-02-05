package com.example.zippy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.zippy.R;
import com.example.zippy.api.Useri;
import com.example.zippy.fragments.Home;
import com.example.zippy.fragments.Post;
import com.example.zippy.fragments.Profile;
import com.example.zippy.fragments.Status;
import com.example.zippy.model.User;
import com.example.zippy.url.Url;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Bottom_nav extends AppCompatActivity {
    BottomNavigationView bnv;
    Fragment selectedFragment = null;
    private int STORAGE_PERMISSION_CODE = 1;
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        bnv = findViewById(R.id.bottom_nav_menu);
        bnv.setOnNavigationItemSelectedListener(selected_nav_items);
        bnv.setSelectedItemId(R.id.nav_home_menu);

        UserPermission();
        GetLoggedUserData();

        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new Home()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener selected_nav_items = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_home_menu:
                    selectedFragment = new Home();
                    break;
                case R.id.nav_advertise_menu:
                    selectedFragment = new Post();
                    break;
                case  R.id.nav_status_menu:
                    selectedFragment = new Status();
                    break;
                case R.id.nav_profile_menu:
                    selectedFragment = new Profile();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,
                    selectedFragment).commit();

            if (selectedFragment != null){
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, selectedFragment).commit();
            }
            return true;
        }
    };


    private void UserPermission() {
        if(ContextCompat.checkSelfPermission( this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        } else {
            requestStoragePermission();
        }

    }

    private void  requestStoragePermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale( this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this).setTitle("permission needed").setMessage("This permission is needed to upload the image")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(Bottom_nav.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this , "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void GetLoggedUserData(){
        Useri useri = Url.getInstance().create(Useri.class);
        Call<User> userCall = useri.getme(Url.token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
