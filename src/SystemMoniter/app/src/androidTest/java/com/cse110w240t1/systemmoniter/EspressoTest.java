package com.cse110w240t1.systemmoniter;

<<<<<<< HEAD
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
=======
        import android.support.test.InstrumentationRegistry;
        import android.support.test.espresso.action.ViewActions;
        import android.support.test.rule.ActivityTestRule;
        import android.support.test.runner.AndroidJUnit4;
        import android.test.ActivityInstrumentationTestCase2;
        import android.test.suitebuilder.annotation.LargeTest;

        import org.junit.After;
        import org.junit.Before;
        import org.junit.Rule;
        import org.junit.Test;
        import org.junit.runner.RunWith;

        import java.util.regex.Matcher;

        import static android.support.test.espresso.Espresso.onData;
        import static android.support.test.espresso.Espresso.onView;
        import static android.support.test.espresso.action.ViewActions.click;
        import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
        import static android.support.test.espresso.action.ViewActions.swipeLeft;
        import static android.support.test.espresso.action.ViewActions.swipeRight;
        import static android.support.test.espresso.action.ViewActions.typeText;
        import static android.support.test.espresso.assertion.ViewAssertions.matches;
        import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
        import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
        import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
        import static android.support.test.espresso.matcher.ViewMatchers.withId;
        import static android.support.test.espresso.matcher.ViewMatchers.withText;
>>>>>>> origin/master

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
<<<<<<< HEAD
    public void tabShouldOpen() {
        onView(withText("SYSTEM INFO")).perform(click());

        onView(withId(R.id.system_info_fragment)).check(matches(withText("SYSTEM INFO")));
=======
    /*public void TestToolBar(){
        onDat(withId(R.id.)).perform(swipeLeft());
    }*/

    public void SwipeGPUShowing(){
        onView(withId(R.id.container)).perform(swipeLeft());
        // onView(withId(R.id.containerABC)).check(matches(withText("OpenGL ES Version")));
    }
    public void TestGPUVersion() {
        onView(withId(R.id.container)).check(matches(withText("OpenGL ES version")));
    }

    public void TestGPUMake() {
        onView(withId(R.id.container)).check(matches(withText("GPU Make")));
    }

    public void ClickOnSystemInfo() {
        onView(withText("SYSTEM INFO")).perform(click());
        onView(withId(R.id.container)).check(matches(withText("OS Version")));
    }

    public void ClickOnGPU() {
        onView(withText("GPU")).perform(click());
    }

    public void TestBattery(){
        onView(withId(R.id.container)).perform(swipeLeft());
        onView(withId(R.id.container)).check(matches(withText("Percentage")));
    }

    public void TestRam(){
        onView(withId(R.id.container)).perform(swipeLeft());
        onView(withId(R.id.container)).check(matches(withText("Total Memory")));
    }

    public void TestGraphExist(){
        onView(withId(R.id.container)).perform(swipeLeft());
        onView(withId(R.id.container)).check(matches(withId(R.id.graph)));
>>>>>>> origin/master
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
