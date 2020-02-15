package com.example.zippy;

import android.app.Activity;

import androidx.test.rule.ActivityTestRule;

import com.example.zippy.activities.Login_Zippy;
import com.example.zippy.activities.Register_Zippy;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LoginITest {
    @Rule
    public ActivityTestRule<Login_Zippy> activityTestRule = new ActivityTestRule<>(Login_Zippy.class);
    @Rule
    public ActivityTestRule<Login_Zippy> activity_loginActivityTestRule = new ActivityTestRule<>(Login_Zippy.class);
    @Test
    public void logintest() {
        onView(withId(R.id.etlogin_Email)).perform(typeText("username"));
        closeSoftKeyboard();
        onView(withId(R.id.etlogin_Password)).perform(typeText("password"));
        closeSoftKeyboard();
//        onView(withId(R.id.btnlogin_user)).perform(click());
    }

}
