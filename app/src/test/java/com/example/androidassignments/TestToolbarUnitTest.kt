package com.example.androidassignments

import org.junit.Assert.assertEquals
import org.junit.Test

class TestToolbarUnitTest {

    @Test
    fun testUpdateSnackbarMessage_withValidInput() {
        val input = "New Snackbar"
        val result = TestToolbar().updateSnackbarMessage(input)
        assertEquals("New Snackbar", result)
    }

    @Test
    fun testUpdateSnackbarMessage_withBlankInput() {
        val input = ""
        val result = TestToolbar().updateSnackbarMessage(input)
        assertEquals("You selected item 1", result)
    }
}
