package com.cse110w240t1.systemmoniter;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
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
    public void SwipeCPUShowing() throws InterruptedException {
        onView(withId(R.id.container)).perform(swipeLeft()).perform(swipeRight());
        onView(ViewMatchers.withTagValue(Matchers.is((Object) "cpufragment"))).check(matches(isEnabled()));
    }

    @Test
    public void SwipeGPUShowing() throws InterruptedException {
        onView(withId(R.id.container)).perform(swipeLeft());
        onView(ViewMatchers.withTagValue(Matchers.is((Object) "gpufragment"))).check(matches(isEnabled()));
    }

    @Test
    public void SwipeBatteryShowing() throws InterruptedException {
        onView(withId(R.id.container)).perform(swipeLeft()).perform(swipeLeft());
        onView(ViewMatchers.withTagValue(Matchers.is((Object) "batteryfragment"))).check(matches(isEnabled()));
    }

    @Test
    public void SwipeRAMShowing() throws InterruptedException {
        onView(withId(R.id.container)).perform(swipeLeft()).perform(swipeLeft())
                .perform(swipeLeft());
        onView(ViewMatchers.withTagValue(Matchers.is((Object) "ramfragment"))).check(matches(isEnabled()));
    }

    @Test
    public void SwipeRAMGraphShowing() throws InterruptedException {
        onView(withId(R.id.container)).perform(swipeLeft())
                .perform(swipeLeft()).perform(swipeLeft())
                .perform(swipeLeft());
        onView(withId(R.id.graph)).check(matches(isDisplayed()));
    }
}
