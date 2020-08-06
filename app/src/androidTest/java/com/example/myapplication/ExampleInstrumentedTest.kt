package com.example.myapplication

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.view.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule
import androidx.test.rule.ActivityTestRule
import com.example.myapplication.EspressoUtil.Companion.hasItemCount
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun onLaunchCheckAmountInputIsDisplayed() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.et_search_bar)).check(matches(isDisplayed()))

        onView(withId(R.id.et_search_bar)).perform(typeTextIntoFocusedView("words"))
        onView(withId(R.id.iv_search_icon)).perform(click())

        Thread.sleep(2000)
        onView(withId(R.id.rcycl_terms)).check(hasItemCount(10))
    }
    @Test
    fun testSortOpen(){
        onView(withId(R.id.iv_sort_icon)).perform(click())
        onView(withId(R.id.lv_sort_options)).check(matches(isDisplayed()))
    }
}
