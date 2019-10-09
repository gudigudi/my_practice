package com.gudigudigudi.apptestespresso;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.google.common.collect.Iterables;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.ext.truth.content.IntentSubject.assertThat;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ChangeTextBehaviorTest {
    public static final String STRING_TO_BE_TYPED = "Espresso";

    @Rule
    public ActivityScenarioRule<MainEspressoActivity> mActivityScenarioRule = new ActivityScenarioRule<MainEspressoActivity>(MainEspressoActivity.class);

    @Before
    public void intentsInit() {
        Intents.init();
    }

    @After
    public void intentsTeardown() {
        Intents.release();
    }

    @Test
    public void changeText_sameActivity() {
        onView(withId(R.id.editTextUserInput))
                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());

        onView(withId(R.id.changeTextBtn))
                .perform(click());

        onView(withId(R.id.textToBeChanged))
                .check(matches(withText(STRING_TO_BE_TYPED)));
    }

    @Test
    public void changeText_newActivity() {
        onView(withId(R.id.editTextUserInput))
                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());

        onView(withId(R.id.activityChangeTextBtn))
                .perform(click());

        onView(ViewMatchers.withId(R.id.show_text_view))
                .check(matches(withText(STRING_TO_BE_TYPED)));

        assertThat(Iterables.getOnlyElement(Intents.getIntents()))
                .hasComponentClass(ShowTextActivity.class);
    }
}
