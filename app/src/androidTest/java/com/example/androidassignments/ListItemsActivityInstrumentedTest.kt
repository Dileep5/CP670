package com.example.androidassignments

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ListItemsActivityInstrumentedTest {

    @Test
    fun testRadioAndSwitch() {
        ActivityScenario.launch(ListItemsActivity::class.java)
        onView(withId(R.id.radioButton)).perform(click())
        onView(withId(R.id.switch1)).perform(click())
    }

    @Test
    fun testCheckboxClick() {
        ActivityScenario.launch(ListItemsActivity::class.java)
        onView(withId(R.id.checkBox)).perform(click())
    }
}
