package com.example.androidassignments

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ChatWindowInstrumentedTest {

    @Test
    fun testSendMessage() {
        ActivityScenario.launch(ChatWindow::class.java)

        onView(withId(R.id.messageInput)).perform(typeText("Hello world!"), closeSoftKeyboard())
        onView(withId(R.id.sendButton)).perform(click())
    }

    @Test
    fun testEmptyMessageShowsToast() {
        ActivityScenario.launch(ChatWindow::class.java)

        onView(withId(R.id.messageInput)).perform(clearText(), closeSoftKeyboard())
        onView(withId(R.id.sendButton)).perform(click())
    }
}
