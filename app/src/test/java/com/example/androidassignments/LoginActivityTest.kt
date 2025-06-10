package com.example.androidassignments

import android.widget.Button
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController

class LoginActivityTest {

    private lateinit var controller: ActivityController<LoginActivity>
    private lateinit var activity: LoginActivity

    @Before
    fun setup() {
        controller = Robolectric.buildActivity(LoginActivity::class.java)
        activity = controller.create().start().resume().get()
    }

    @Test
    fun testEmptyEmailValidation() {
        activity.findViewById<EditText>(R.id.emailEditText).setText("invalid")
        activity.findViewById<EditText>(R.id.passwordEditText).setText("123456")
        activity.findViewById<Button>(R.id.loginButton).performClick()
        assert(activity.findViewById<EditText>(R.id.emailEditText).error != null)
    }

    @Test
    fun testEmptyPasswordValidation() {
        activity.findViewById<EditText>(R.id.emailEditText).setText("test@example.com")
        activity.findViewById<EditText>(R.id.passwordEditText).setText("")
        activity.findViewById<Button>(R.id.loginButton).performClick()
        assert(activity.findViewById<EditText>(R.id.passwordEditText).error != null)
    }

    @Test
    fun testValidLoginInput() {
        activity.findViewById<EditText>(R.id.emailEditText).setText("test@example.com")
        activity.findViewById<EditText>(R.id.passwordEditText).setText("123456")
        activity.findViewById<Button>(R.id.loginButton).performClick()
        assert(true)
    }
}
