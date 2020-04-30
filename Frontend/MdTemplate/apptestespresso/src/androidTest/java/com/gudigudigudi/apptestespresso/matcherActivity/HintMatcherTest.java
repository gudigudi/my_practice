package com.gudigudigudi.apptestespresso.matcherActivity;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.gudigudigudi.apptestespresso.MatcherActivity;
import com.gudigudigudi.apptestespresso.R;
import com.gudigudigudi.apptestespresso.matcher.HintMatcher;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HintMatcherTest {

    private static final String INVALID_STRING_TO_BE_TYPED = "Earl Grey";
    private static final String COFFEE_ENDING = "coffee?";
    private static final String COFFEE_INVALID_ENDING = "tea?";

    private String mStringWithValidEnding;
    private String mValidStringToBeTyped;

    @Rule
    public ActivityScenarioRule<MatcherActivity> mActivityScenarioRule = new ActivityScenarioRule<>(MatcherActivity.class);

    @Before
    public void initValidStrings() {
        mStringWithValidEnding = "Random " + MatcherActivity.VALID_ENDING;

        mValidStringToBeTyped = MatcherActivity.COFFEE_PREPARATIONS.get(0);
    }

    @Test
    public void hint_isDiaplayedInEditText() {
        String hintText = getApplicationContext().getResources().getString(R.string.hint);

        onView(withId(R.id.editText)).check(matches(HintMatcher.withHint(hintText)));
    }

    @Test
    public void hint_endsWith() {
        onView(withId(R.id.editText))
                .check(matches(HintMatcher.withHint(anyOf(endsWith(COFFEE_ENDING), endsWith(COFFEE_INVALID_ENDING)))));
    }

    @Test
    public void editText_canBeTypedInto() {
        onView(withId(R.id.editText))
                .perform(typeText(mValidStringToBeTyped), closeSoftKeyboard())
                .check(matches(withText(mValidStringToBeTyped)));
    }

    @Test
    public void validation_resultIsOneOfTheValidStrings() {
        onView(withId(R.id.editText))
                .perform(typeText(mValidStringToBeTyped), closeSoftKeyboard());

        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.txtv_input_validation_success)).check(matches(isDisplayed()));
        onView(withId(R.id.txtv_input_validation_error)).check(matches(not(isDisplayed())));
    }

    @Test
    public void validation_resultHasCorrectEnding() {
        onView(withId(R.id.editText))
                .perform(typeText(mStringWithValidEnding), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.txtv_input_validation_success)).check(matches(isDisplayed()));
        onView(withId(R.id.txtv_input_validation_error)).check(matches(not(isDisplayed())));
    }

    @Test
    public void validation_resultIsIncorrect() {
        onView(withId(R.id.editText))
                .perform(typeText(INVALID_STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.txtv_input_validation_success)).check(matches(not(isDisplayed())));
        onView(withId(R.id.txtv_input_validation_error)).check(matches(isDisplayed()));
    }
}