package com.cse110w240t1.systemmoniter;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void tabShouldOpen() {
        onView(withText("SYSTEM INFO")).perform(click());

        onView(withId(R.id.system_info_fragment)).check(matches(withText("SYSTEM INFO")));
    }


    @Test
    public void tabShouldSwypeLeft() {
        onView(withId(R.id.ram_fragment)).perform(swipeLeft());

        onView(withId(R.id.ram_fragment)).check(matches(withText("RAM")));
    }

    @Test
    public void tabShouldSwypeRight() {
        onView(withId(R.id.gpu_fragment)).perform(swipeRight());

        onView(withId(R.id.gpu_fragment)).check(matches(withText("GPU")));
    }

    @Test
    public void shouldBeAbleToLaunchMainScreen() {
        onView(withText("System Monitor")).check(ViewAssertions.matches(isDisplayed()));
    }
}
