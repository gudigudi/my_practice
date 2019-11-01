package com.gudigudigudi.appdemojetpack.dagger2;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.gudigudigudi.appdemojetpack.R;
import com.gudigudigudi.appdemojetpack.ToastMatcher;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DaggerActivityTest {

    String strToast = "I eat 豆沙包    康师傅方便面 from 王小二包子店";
    String strToast2 = "I eat 豆沙包    康师傅方便面 from 沙县小吃";
    String strToast3 = "testValue is 1234567890";
    @Rule
    public ActivityScenarioRule<DaggerActivity> mActivityScenarioRule = new ActivityScenarioRule<>(DaggerActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void clickBtn() {
        onView(withId(R.id.btn)).perform(click());
        onView(withText(strToast)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Test
    public void clickBtn2() {
        onView(withId(R.id.btn2)).perform(click());
        onView(withText(strToast2)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Test
    public void clickBtn3() {
        onView(withId(R.id.btn3)).perform(click());
        onView(withText(strToast3)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }
}