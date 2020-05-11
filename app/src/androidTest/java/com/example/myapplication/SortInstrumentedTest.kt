package com.example.myapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.view.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule
import androidx.test.rule.ActivityTestRule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class SortInstrumentedTest {

    @get:Rule
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    @Test
    fun testSortOpen(){
        onView(withId(R.id.iv_sort_icon)).perform(click())
        onView(withId(R.id.lv_sort_icon)).check(matches(isDisplayed()))
    }
}
