package com.example.zippy;

import androidx.test.rule.ActivityTestRule;

import com.example.zippy.activities.Register_Zippy;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class SignupTest {

    @Rule
    public ActivityTestRule<Register_Zippy> register_zippyActivityTestRule = new ActivityTestRule<>(Register_Zippy.class);

    @Test
    public void TestSignup(){
        onView(withId(R.id.et_fname))
                .perform(typeText("rupesh"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.et_lname))
                .perform(typeText("dhakal"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.et_email))
                .perform(typeText("rd@gmail.com"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.et_phone))
                .perform(typeText("9638527412"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.et_uname))
                .perform(typeText("rd5"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.et_password))
                .perform(typeText("rd5"))
                .perform(closeSoftKeyboard());
    }

}

