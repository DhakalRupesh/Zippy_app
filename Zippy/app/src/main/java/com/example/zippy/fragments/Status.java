package com.example.zippy.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zippy.R;
import com.example.zippy.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class Status extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView go_to_login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_status, container, false);

        tabLayout = view.findViewById(R.id.tab_Navigate);
        viewPager = view.findViewById(R.id.viewpager);

        ViewPagerAdapter viewPageAdapter = new ViewPagerAdapter(getFragmentManager());

        viewPageAdapter.addFragment(new YourAdvertise(), "YOUR ADVERTISE");
        viewPageAdapter.addFragment(new BookedAdvertise(), "YOUR BOOKINGS");

        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}
