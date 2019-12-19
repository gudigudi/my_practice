package com.gudigudigudi.appdemojetpack.business.userProfile


import android.view.View
import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.gudigudigudi.appdemojetpack.R
import junit.framework.Assert.assertTrue
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class UserProfileFragmentTest {

    @Before
    fun setUp(){
        val scenario = launchFragmentInContainer<UserProfileFragment>()
    }

    @Test
    fun viewInited() {
        onView(withId(R.id.user_name)).check(matches(withText("22")))
    }

    @Test
    fun clickBtn(){
        onView(withId(R.id.btn)).perform(click())

        val s1 = getText(onView(withId(R.id.user_name)))
        val s2 = getText(onView(withId(R.id.edit_text)))
        assertTrue(s1.equals(s2))
    }

    fun getText(matcher: ViewInteraction): String {
        var text = String()
        matcher.perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return "Text of the view"
            }

            override fun perform(uiController: UiController, view: View) {
                val tv = view as TextView
                text = tv.text.toString()
            }
        })

        return text
    }
}