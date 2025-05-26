package com.example.androidassignments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var sharedPrefs: SharedPreferences

    private val tag = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "inside onCreate")
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        sharedPrefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        val savedEmail = sharedPrefs.getString("DefaultEmail", "email@domain.com")
        emailEditText.setText(savedEmail)

        loginButton.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString()

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.error = "Please enter a valid email"
            return
        }

        if (password.isEmpty()) {
            passwordEditText.error = "Password cannot be empty"
            return
        }

        sharedPrefs.edit {
            putString("DefaultEmail", email)
        }

        print("Login Successful")

        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
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
