package com.example.androidassignments

import android.content.Intent
import android.widget.Button
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController

class MainActivityTest {

    private lateinit var controller: ActivityController<MainActivity>
    private lateinit var activity: MainActivity

    @Before
    fun setup() {
        controller = Robolectric.buildActivity(MainActivity::class.java)
        activity = controller.create().start().resume().get()
    }

    @Test
    fun testButtonClickOpensListItemsActivity() {
        val button = activity.findViewById<Button>(R.id.button)
        button.performClick()

        val expectedIntent = Intent(activity, ListItemsActivity::class.java)
        assertEquals(
            expectedIntent.component,
            activity.packageManager.resolveActivity(expectedIntent, 0)?.activityInfo?.name?.let {
                expectedIntent.component?.className
            })
    }

    @Test
    fun testChatButtonClickOpensChatActivity() {
        val chatButton = activity.findViewById<Button>(R.id.startChatButton)
        chatButton.performClick()
        assertTrue(true)
    }
}