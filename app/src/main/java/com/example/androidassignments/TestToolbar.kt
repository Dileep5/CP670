package com.example.androidassignments

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar

class TestToolbar : AppCompatActivity() {

    private var snackbarMessage: String = "You selected item 1"

    fun updateSnackbarMessage(input: String): String {
        return if (input.isBlank()) "You selected item 1" else input
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_toolbar)

        val toolbar: Toolbar = findViewById(R.id.my_toolbar)
        setSupportActionBar(toolbar)

        val fab: com.google.android.material.floatingactionbutton.FloatingActionButton =
            findViewById(R.id.fab)
        fab.setOnClickListener {
            Snackbar.make(it, "Floating action clicked", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_one -> {
                Snackbar.make(findViewById(R.id.my_toolbar), snackbarMessage, Snackbar.LENGTH_SHORT).show()
                return true
            }
            R.id.action_two -> {
                showConfirmDialog()
                return true
            }
            R.id.action_three -> {
                showCustomInputDialog()
                return true
            }
            R.id.action_about -> {
                Toast.makeText(this, "Version 1.0, by Venkata Sai Dileep Varada", Toast.LENGTH_LONG).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showConfirmDialog() {
        AlertDialog.Builder(this)
            .setTitle("Do you want to go back?")
            .setPositiveButton("Yes") { _, _ -> finish() }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showCustomInputDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
        val inputField = dialogView.findViewById<EditText>(R.id.edit_message)

        AlertDialog.Builder(this)
            .setTitle("Set New Snackbar Message")
            .setView(dialogView)
            .setPositiveButton("Set") { _, _ ->
                snackbarMessage = updateSnackbarMessage(inputField.text.toString())
                Toast.makeText(this, "Message updated", Toast.LENGTH_SHORT).show()
            }

            .setNegativeButton("Cancel", null)
            .show()
    }
}
