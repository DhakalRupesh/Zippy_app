package com.example.zippy;

import androidx.test.rule.ActivityTestRule;

import com.example.zippy.activities.EditProfile;
import com.example.zippy.activities.NearetLocation_map;
import com.example.zippy.fragments.Post;
import com.example.zippy.model.Advertise;

import org.junit.Rule;
import org.junit.Test;

public class post {
    @Rule
    public ActivityTestRule<NearetLocation_map> UpdateActivityTestRule = new ActivityTestRule<>(NearetLocation_map.class);
    @Test
    public void TestMap() throws Exception {

    }
}
