package com.cse110w240t1.systemmoniter;

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

        import static android.support.test.espresso.Espresso.onView;
        import static android.support.test.espresso.action.ViewActions.click;
        import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
        import static android.support.test.espresso.action.ViewActions.swipeLeft;
        import static android.support.test.espresso.action.ViewActions.typeText;
        import static android.support.test.espresso.assertion.ViewAssertions.matches;
        import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
        import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
        import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
        import static android.support.test.espresso.matcher.ViewMatchers.withId;
        import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    /*public void TestToolBar(){
        onView(withId(R.id.tabs)).perform(swipeLeft());
    }*/
    public void TestGPUShowing(){
        onView(withText("GPU")).perform(click());
        onView(withId(R.id.container)).check(matches(withText("OpenGL ES Version")));
    }

    public void TestBattery(){
        onView(withId(R.id.container)).perform(swipeLeft());
        onView(withId(R.id.container)).check(matches(withText("Percentage")));
    }

    public void TestRam(){
        onView(withId(R.id.container)).perform(swipeLeft());
        onView(withId(R.id.container)).check(matches(withText("Total Memory")));
    }

}
