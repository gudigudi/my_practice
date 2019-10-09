package com.gudigudigudi.apptestespresso.matcher;

import android.view.View;
import android.widget.EditText;

import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static androidx.test.espresso.intent.Checks.checkNotNull;
import static org.hamcrest.Matchers.is;

/**
 * A custom matcher that checks the hint property of an {@link EditText}. It
 * accepts either a {@link String} or a {@link Matcher}.
 */
public class HintMatcher {

    public static Matcher<View> withHint(final String substring) {
        return withHint(is(substring));
    }

    public static Matcher<View> withHint(final Matcher<String> stringMatcher) {
        checkNotNull(stringMatcher);
        return new BoundedMatcher<View, EditText>(EditText.class) {

            @Override
            public boolean matchesSafely(EditText view) {
                final CharSequence hint = view.getHint();
                return hint != null && stringMatcher.matches(hint.toString());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with hint: ");
                stringMatcher.describeTo(description);
            }
        };
    }
}
