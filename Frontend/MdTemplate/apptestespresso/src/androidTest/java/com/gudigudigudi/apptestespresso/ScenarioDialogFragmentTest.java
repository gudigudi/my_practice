package com.gudigudigudi.apptestespresso;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.Lifecycle;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class ScenarioDialogFragmentTest {

    @Test
    public void testInitFragment() {

        FragmentScenario<ScenarioDialogFragment> scenario = FragmentScenario.launchInContainer(ScenarioDialogFragment.class);
        scenario.recreate();

        scenario.moveToState(Lifecycle.State.RESUMED);
        onView(withId(R.id.textView))
                .check(matches(withText("Hello blank fragment")));
    }
}
