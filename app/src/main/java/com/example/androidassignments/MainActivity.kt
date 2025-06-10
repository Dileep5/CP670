package com.example.androidassignments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.enableEdgeToEdge

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var startChatButton: Button
    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Log.d(tag, "inside onCreate")

        button = findViewById(R.id.button)
        button.setOnClickListener {
            Log.d(tag, "Button clicked, launching ListItemsActivity")
            print("Opening list...")
            val intent = Intent(this, ListItemsActivity::class.java)
            startActivityForResult(intent, 10)
        }

        startChatButton = findViewById(R.id.startChatButton)
        startChatButton.setOnClickListener {
            Log.i(tag, "User clicked Start Chat")
            print("Starting chat...")
            val chatIntent = Intent(this, ChatWindow::class.java)
            startActivity(chatIntent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(tag, "onActivityResult called")
        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            val response = data?.getStringExtra("Response") ?: "No response"
            Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "inside onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "inside onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "inside onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "inside onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "inside onDestroy")
    }

    private fun print(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
