package com.gudigudigudi.appdemojetpack.viewpager2;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.gudigudigudi.appdemojetpack.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.core.AllOf.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ViewPager2ActivityTest {

    private List<String> eventList = new ArrayList<>(Arrays.asList("0", "1", "2"));

    @Rule
    public ActivityScenarioRule<ViewPager2Activity> mActivityScenarioRule = new ActivityScenarioRule<>(ViewPager2Activity.class);

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
        onView(withId(R.id.tabLayout))
                .check(matches(isDisplayed()));
        onView(withId(R.id.viewpager))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.textview), withClassName(endsWith("TextView"))))
                .check(matches(withText("Page " + eventList.get(0))));
    }

    @Ignore
    @Test
    public void swipeToNextPage() {
        onView(withId(R.id.viewpager))
                .perform(swipeLeft());
        onView(allOf(withId(R.id.textview), withClassName(endsWith("TextView"))))
                .check(matches(withText("Page " + eventList.get(1))));
    }

    @Test
    public void swipeToPreviousPage() {
        onView(withId(R.id.viewpager))
                .perform(swipeLeft(), swipeLeft(), swipeRight());
        onView(allOf(withId(R.id.textview), withClassName(endsWith("TextView"))))
                .check(matches(withText("Page " + eventList.get(1))));
    }

    @Ignore
    @Test
    public void swipeToLastPage(){

    }

    @Ignore
    @Test
    public void swipeTofirstPage(){

    }
}