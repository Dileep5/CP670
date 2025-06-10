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
class LoginActivityInstrumentedTest {

    @Test
    fun testLoginValidation() {
        ActivityScenario.launch(LoginActivity::class.java)

        onView(withId(R.id.emailEditText)).perform(typeText("invalidemail"), closeSoftKeyboard())
        onView(withId(R.id.passwordEditText)).perform(typeText("123456"), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())

    }

    @Test
    fun testLoginSuccessPath() {
        ActivityScenario.launch(LoginActivity::class.java)

        onView(withId(R.id.emailEditText)).perform(typeText("test@example.com"), closeSoftKeyboard())
        onView(withId(R.id.passwordEditText)).perform(typeText("123456"), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())
    }
}
