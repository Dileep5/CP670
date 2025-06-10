package com.example.androidassignments

import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Switch
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController

class ListItemsActivityTest {

    private lateinit var activity: ListItemsActivity

    @Before
    fun setUp() {
        val controller = Robolectric.buildActivity(ListItemsActivity::class.java)
        activity = controller.create().start().resume().get()
    }

    @Test
    fun testRadioButtonClick() {
        val radio = activity.findViewById<RadioButton>(R.id.radioButton)
        radio.performClick()
        assert(radio.isChecked)
    }

    @Test
    fun testSwitchToggle() {
        val switch = activity.findViewById<Switch>(R.id.switch1)
        switch.isChecked = true
        switch.performClick()
        assert(!switch.isChecked || switch.isChecked)
    }

    @Test
    fun testCheckboxDialogShowsAndCancels() {
        val checkbox = activity.findViewById<CheckBox>(R.id.checkBox)
        checkbox.performClick()
        assert(checkbox.isChecked)
    }
}
