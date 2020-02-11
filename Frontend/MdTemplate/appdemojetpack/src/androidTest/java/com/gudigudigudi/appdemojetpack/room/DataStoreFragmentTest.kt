package com.gudigudigudi.appdemojetpack.room

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.gudigudigudi.appdemojetpack.MatcherUtil
import com.gudigudigudi.appdemojetpack.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class DataStoreFragmentTest {
    @Before
    fun setUp() {
        val scenario = launchFragmentInContainer<DataStoreFragment>()
    }

    @Test
    fun viewInited() {
        var viewIds = intArrayOf(R.id.edit, R.id.btn_save_data_by_sharedpreferences, R.id.btn_restore_data_by_sharedpreferences, R.id.btn_insert_room, R.id.btn_query_room, R.id.btn_update_room,R.id.btn_delete_room)
        MatcherUtil.checkMultiViews(isDisplayed(), viewIds)
    }
}