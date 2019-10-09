package com.gudigudigudi.apptestespresso;

import android.content.Intent;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.web.webdriver.DriverAtoms;
import androidx.test.espresso.web.webdriver.Locator;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static androidx.test.espresso.web.sugar.Web.onWebView;
import static androidx.test.espresso.web.webdriver.DriverAtoms.clearElement;
import static androidx.test.espresso.web.webdriver.DriverAtoms.findElement;
import static androidx.test.espresso.web.webdriver.DriverAtoms.getText;
import static androidx.test.espresso.web.webdriver.DriverAtoms.webClick;
import static org.hamcrest.Matchers.containsString;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class WebViewActivityTest {

    private static final String MACCHIATO = "Macchiato";
    private static final String DOPPIO = "Doppio";

    @Rule
    public ActivityTestRule<WebViewActivity> mActivityTestRule =
            new ActivityTestRule<WebViewActivity>(WebViewActivity.class, false, false) {

                @Override
                protected void afterActivityLaunched() {
                    onWebView().forceJavascriptEnabled();
                }
            };

    @Test
    public void typeTextInput_clickButton_SubmitsForm() {
        mActivityTestRule.launchActivity(withWebFormIntent());

        onWebView().withElement(findElement(Locator.ID, "text_input"))
                .perform(clearElement())
                .perform(DriverAtoms.webKeys(MACCHIATO))
                .withElement(findElement(Locator.ID, "submitBtn"))
                .perform(webClick())
                .withElement(findElement(Locator.ID, "response"))
                .check(webMatches(getText(), containsString(MACCHIATO)));
    }

    @Test
    public void typeTextInput_clickButton_ChangesText() {
        // Lazily launch the Activity with a custom start Intent per test
        mActivityTestRule.launchActivity(withWebFormIntent());

        // Selects the WebView in your layout. If you have multiple WebViews you can also use a
        // matcher to select a given WebView, onWebView(withId(R.id.web_view)).
        onWebView(ViewMatchers.withId(R.id.web_view))
                .withElement(findElement(Locator.ID, "text_input"))
                .perform(clearElement())
                .perform(DriverAtoms.webKeys(DOPPIO))
                .withElement(findElement(Locator.ID, "changeTextBtn"))
                .perform(webClick())
                .withElement(findElement(Locator.ID, "message"))
                .check(webMatches(getText(), containsString(DOPPIO)));
    }

    /**
     * @return start {@link Intent} for the simple web form URL.
     */
    private static Intent withWebFormIntent() {
        Intent basicFormIntent = new Intent();
        basicFormIntent.putExtra(WebViewActivity.KEY_URL_TO_LOAD, WebViewActivity.WEB_FORM_URL);
        return basicFormIntent;
    }
}