package com.gudigudigudi.apptestespresso;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MultiWindowTest {

    @Rule
    public ActivityTestRule<SuggestActivity> mActivityTestRule = new ActivityTestRule<>(SuggestActivity.class);

    private SuggestActivity mActivity = null;

    @Before
    public void setActivity() {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void autoCompleteTextView_twoSuggestions() {
        onView(ViewMatchers.withId(R.id.auto_complete_text_view))
                .perform(typeText("So"), closeSoftKeyboard());
        onView(withText("South China Sea"))
                .inRoot(withDecorView(Matchers.not(Matchers.is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        onView(withText("Southern Ocean"))
                .inRoot(withDecorView(Matchers.not(Matchers.is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void autoCompleteTextView_oneSuggestions() {
        onView(ViewMatchers.withId(R.id.auto_complete_text_view))
                .perform(typeText("South "), closeSoftKeyboard());
        onView(withText("South China Sea"))
                .inRoot(withDecorView(Matchers.not(Matchers.is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        onView(withText("Southern Ocean"))
                .inRoot(withDecorView(Matchers.not(Matchers.is(mActivity.getWindow().getDecorView()))))
                .check(doesNotExist());
    }

    @Test
    public void autoCompleteTextView_clickAndCheck() {
        // Type text into the text view
        onView(ViewMatchers.withId(R.id.auto_complete_text_view))
                .perform(typeTextIntoFocusedView("South "), closeSoftKeyboard());

        // Tap on a suggestion.
        onView(withText("South China Sea"))
                .inRoot(withDecorView(Matchers.not(Matchers.is(mActivity.getWindow().getDecorView()))))
                .perform(click());

        // By clicking on the auto complete term, the text should be filled in.
        onView(ViewMatchers.withId(R.id.auto_complete_text_view))
                .check(matches(withText("South China Sea")));
    }

    @Test
    public void autoCompleteTextView_onDataClickAndCheck() {
        // NB: The autocompletion box is implemented with a ListView, so the preferred way
        // to interact with it is onData(). We can use inRoot here too!
        onView(ViewMatchers.withId(R.id.auto_complete_text_view))
                .perform(typeText("S"), closeSoftKeyboard());

        // This is useful because some of the completions may not be part of the View Hierarchy
        // unless you scroll around the list.
        onData(allOf(instanceOf(String.class), is("Baltic Sea")))
                .inRoot(withDecorView(Matchers.not(Matchers.is(mActivity.getWindow().getDecorView()))))
                .perform(click());

        // The text should be filled in.
        onView(ViewMatchers.withId(R.id.auto_complete_text_view))
                .check(matches(withText("Baltic Sea")));
    }
}
