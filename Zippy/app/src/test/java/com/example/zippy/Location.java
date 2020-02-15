package com.example.zippy;

import com.example.zippy.activities.NearetLocation_map;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

public class Location {
    NearetLocation_map nearetLocation_map = new NearetLocation_map();
    @Test
    public void isLocation() {
        assertTrue(nearetLocation_map.checkUserLocationPermission());
    }
}
