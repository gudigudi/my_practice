package com.gudigudigudi.appdemojetpack.databinding;

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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DataBindingActivityTest {

    @Rule
    public ActivityScenarioRule<DataBindingActivity> mActivityScenarioRule = new ActivityScenarioRule<>(DataBindingActivity.class);

    @Before
    public void setUp() throws Exception {
        Intents.init();
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

    @Test
    public void viewInited(){
        onView(withId(R.id.txt)).check(matches(withText("set data")));
    }

    @Test
    public void textViewClicked(){
        onView(withId(R.id.txt)).perform(click())
                .check(matches(withText("click data")));
    }
}