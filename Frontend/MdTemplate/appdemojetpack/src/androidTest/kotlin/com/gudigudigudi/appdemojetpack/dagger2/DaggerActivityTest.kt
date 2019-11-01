package com.gudigudigudi.appdemojetpack.dagger2

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.gudigudigudi.appdemojetpack.R
import com.gudigudigudi.appdemojetpack.ToastMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class DaggerActivityTest {

    var strToast = "I eat 豆沙包    康师傅方便面 from 王小二包子店"
    var strToast2 = "I eat 豆沙包    康师傅方便面 from 沙县小吃"
    var strToast3 = "testValue is 1234567890"

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<DaggerActivity> = ActivityScenarioRule(DaggerActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun clickBtn() {
        onView(withId(R.id.btn)).perform(click())
        onView(withText(strToast)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun clickBtn2() {
        onView(withId(R.id.btn2)).perform(click())
        onView(withText(strToast2)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun clickBtn3() {
        onView(withId(R.id.btn3)).perform(click())
        onView(withText(strToast3)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }
}