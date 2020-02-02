package com.example.zippy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.zippy.R;
import com.example.zippy.fragments.Home;
import com.example.zippy.fragments.Post;
import com.example.zippy.fragments.Profile;
import com.example.zippy.fragments.Status;
import com.example.zippy.fragments.home_details;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Bottom_nav extends AppCompatActivity {
    BottomNavigationView bnv;
    Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        bnv = findViewById(R.id.bottom_nav_menu);
        bnv.setOnNavigationItemSelectedListener(selected_nav_items);
        bnv.setSelectedItemId(R.id.nav_home_menu);
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
//                    menuItem.setIcon(R.drawable.instalikeselected);
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,
                    selectedFragment).commit();

            if (selectedFragment != null){
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, selectedFragment).commit();
            }
            return true;
        }
    };
}
