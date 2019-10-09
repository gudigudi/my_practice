package com.gudigudigudi.apptestespresso;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecyclerViewActivityTest {

    private static final int ITEM_BELOW_THE_FOLD = 40;

    @Rule
    public ActivityTestRule<RecyclerViewActivity> mActivityTestRule = new ActivityTestRule<>(RecyclerViewActivity.class);

    @Test
    public void scrollToItemBelowFold_checkItsText() {
        onView(withId(R.id.recyclerView))
                .perform(actionOnItemAtPosition(ITEM_BELOW_THE_FOLD, click()));

        String itemElementText = getApplicationContext().getResources().getString(R.string.item_element_text) + ITEM_BELOW_THE_FOLD;
        onView(withText(itemElementText)).check(matches(isDisplayed()));
    }

    @Test
    public void itemInMiddleOfList_hasSpecialText() {
        String middleElementText =
                getApplicationContext().getResources().getString(R.string.middle);

        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()));
        onView(withText(middleElementText))
                .check(matches(isDisplayed()));
    }

    private static Matcher<CustomAdapter.VH> isInTheMiddle() {
        return new TypeSafeMatcher<CustomAdapter.VH>() {
            @Override
            protected boolean matchesSafely(CustomAdapter.VH customHolder) {
                return customHolder.isInTheMiddle();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("item in the middle");
            }
        };
    }
}