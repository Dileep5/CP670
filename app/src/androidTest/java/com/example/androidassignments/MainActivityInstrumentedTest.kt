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
class MainActivityInstrumentedTest {

    @Test
    fun testButtonNavigationToListItems() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.button)).perform(click())
    }

    @Test
    fun testButtonNavigationToChat() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.startChatButton)).perform(click())
    }
}
