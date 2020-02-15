package com.example.zippy;

import androidx.test.rule.ActivityTestRule;

import com.example.zippy.activities.EditProfile;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class UpdateTest {
    @Rule
    public ActivityTestRule<EditProfile> UpdateActivityTestRule = new ActivityTestRule<>(EditProfile.class);

    @Test
    public void TestUpdateUser() throws Exception{
        onView(withId(R.id.et_pro_uname))
                .perform(typeText("ram"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.et_pro_email))
                .perform(typeText("ram@gmail.com"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.et_pro_description))
                .perform(typeText("ram@gmail.com"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.btn_pro_update))
                .perform(click());


    }
}
