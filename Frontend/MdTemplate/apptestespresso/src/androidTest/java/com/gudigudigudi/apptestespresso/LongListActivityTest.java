package com.gudigudigudi.apptestespresso;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LongListActivityTest {

    private static final String TEXT_ITEM_30 = "item: 30";
    private static final String TEXT_ITEM_30_SELECTED = "30";
    private static final String TEXT_ITEM_60 = "item: 60";
    private static final String LAST_ITEM_ID = "item: 99";

    @Rule
    public ActivityScenarioRule<LongListActivity> mRule = new ActivityScenarioRule<>(LongListActivity.class);

    private static DataInteraction onRow(String str) {
        return onData(hasEntry(equalTo(LongListActivity.ROW_TEXT), is(str)));
    }

    /**
     * Test that the list is long enough for this sample, the last item shouldn't appear.
     */
    @Test
    public void lastItem_NotDisplayed() {
        onView(withText(LAST_ITEM_ID))
                .check(doesNotExist());
    }

    /**
     * Check that the item is created. onData() takes care of scrolling.
     */
    @Test
    public void list_Scrolls() {
        onRow(LAST_ITEM_ID)
                .check(matches(isCompletelyDisplayed()));
    }

    /**
     * Clicks on a row and checks that the activity detected the click.
     */
    @Test
    public void row_Click() {
        onRow(TEXT_ITEM_30).onChildView(ViewMatchers.withId(R.id.rowContentTextView))
                .perform(click());

        onView(ViewMatchers.withId(R.id.selection_row_value))
                .check(matches(withText(TEXT_ITEM_30_SELECTED)));
    }

    /**
     * Checks that a toggle button is checked after clicking on it.
     */
    @Test
    public void toggle_Click() {
        onRow(TEXT_ITEM_30).onChildView(ViewMatchers.withId(R.id.rowToggleButton))
                .perform(click());

        onRow(TEXT_ITEM_30).onChildView(ViewMatchers.withId(R.id.rowToggleButton))
                .check(matches(isEnabled()));
    }

    /**
     * Make sure that clicking on the toggle button doesn't trigger a click on the row.
     */
    @Test
    public void toggle_ClickDoesntPropagate() {
        onRow(TEXT_ITEM_60).onChildView(ViewMatchers.withId(R.id.rowToggleButton))
                .perform(click());

        onRow(TEXT_ITEM_30).onChildView(ViewMatchers.withId(R.id.rowContentTextView))
                .perform(click());

        onView(ViewMatchers.withId(R.id.selection_row_value))
                .check(matches(withText(TEXT_ITEM_30_SELECTED)));
    }
}