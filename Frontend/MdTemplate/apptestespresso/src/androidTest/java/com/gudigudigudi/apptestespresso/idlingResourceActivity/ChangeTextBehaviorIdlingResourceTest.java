package com.gudigudigudi.apptestespresso.idlingResourceActivity;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.gudigudigudi.apptestespresso.IdlingResourceActivity;
import com.gudigudigudi.apptestespresso.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ChangeTextBehaviorIdlingResourceTest {

    private static final String STRING_TO_BE_TYPED = "Espresso";

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        ActivityScenario activityScenario = ActivityScenario.launch(IdlingResourceActivity.class);
        activityScenario.onActivity((ActivityScenario.ActivityAction<IdlingResourceActivity>) activity -> {
            mIdlingResource = activity.getIdlingResource();
            IdlingRegistry.getInstance().register(mIdlingResource);
        });
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }

    @Test
    public void changeText_sameActivity() {
        onView(ViewMatchers.withId(R.id.editTextUserInput))
                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.changeTextBtn))
                .perform(click());
        onView(ViewMatchers.withId(R.id.textToBeChanged))
                .check(matches(withText(STRING_TO_BE_TYPED)));
    }
}
