package com.gudigudigudi.appdemojetpack.viewpager2;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.gudigudigudi.appdemojetpack.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ScreenSlidePagerActivityTest {

    @Rule
    public ActivityScenarioRule<ScreenSlidePagerActivity> mActivityScenarioRule = new ActivityScenarioRule<>(ScreenSlidePagerActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void teardown() {
        Intents.release();
    }

    @Test
    public void viewInited() {
        onView(withId(R.id.pager))
                .check(matches(isDisplayed()));
    }
}