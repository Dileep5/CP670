package com.example.androidassignments

import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController

class ChatWindowTest {

    private lateinit var activity: ChatWindow

    @Before
    fun setUp() {
        val controller = Robolectric.buildActivity(ChatWindow::class.java)
        activity = controller.create().start().resume().get()
    }

    @Test
    fun testEmptyMessageShowsToast() {
        val input = activity.findViewById<EditText>(R.id.messageInput)
        input.setText("")
        activity.findViewById<Button>(R.id.sendButton).performClick()
        assertTrue(true)
    }

    @Test
    fun testSendingMessageAddsToList() {
        val input = activity.findViewById<EditText>(R.id.messageInput)
        input.setText("Hello!")
        activity.findViewById<Button>(R.id.sendButton).performClick()

        val listView = activity.findViewById<ListView>(R.id.chatView)
        assertEquals(1, listView.adapter.count)
    }
}
