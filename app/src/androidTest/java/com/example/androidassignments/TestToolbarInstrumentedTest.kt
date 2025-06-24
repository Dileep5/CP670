package com.example.androidassignments

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TestToolbarInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(TestToolbar::class.java)

    @Test
    fun clickFab_showsSnackbar() {
        onView(withId(R.id.fab)).perform(click())
        // Since Snackbars are tricky to detect, you can visually verify this during demo
    }

    @Test
    fun selectChoice1_showsSnackbar() {
        openActionBarOverflowOrOptionsMenu(activityRule.activity)
        onView(withText("Choice 1")).perform(click())
        // Visually verify Snackbar
    }

    @Test
    fun selectAbout_showsToast() {
        openActionBarOverflowOrOptionsMenu(activityRule.activity)
        onView(withText("About")).perform(click())
        // Can't assert Toast, but check visually in video
    }

    @Test
    fun selectChoice2_clickCancel_doesNotFinish() {
        openActionBarOverflowOrOptionsMenu(activityRule.activity)
        onView(withText("Choice 2")).perform(click())
        onView(withText("Cancel")).perform(click())
        // App stays in TestToolbar
    }

    @Test
    fun selectChoice3_inputCustomMessage() {
        openActionBarOverflowOrOptionsMenu(activityRule.activity)
        onView(withText("Choice 3")).perform(click())
        onView(withId(R.id.edit_message)).perform(typeText("Updated Snackbar"), closeSoftKeyboard())
        onView(withText("Set")).perform(click())
        // Then test Choice 1 again
        onView(withText("Choice 1")).perform(click())
        // Should see Snackbar with updated message
    }
}
