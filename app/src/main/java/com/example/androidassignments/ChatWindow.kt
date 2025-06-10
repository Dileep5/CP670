package com.example.androidassignments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ChatWindow : AppCompatActivity() {

    private lateinit var chatView: ListView
    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button

    private val messages = mutableListOf<String>()
    private lateinit var adapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        chatView = findViewById(R.id.chatView)
        messageInput = findViewById(R.id.messageInput)
        sendButton = findViewById(R.id.sendButton)

        adapter = ChatAdapter(this)
        chatView.adapter = adapter

        sendButton.setOnClickListener {
            val message = messageInput.text.toString().trim()
            if (message.isNotEmpty()) {
                messages.add(message)
                adapter.notifyDataSetChanged()
                chatView.smoothScrollToPosition(messages.size - 1)
                messageInput.text.clear()
            } else {
                Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    inner class ChatAdapter(context: android.content.Context) : ArrayAdapter<String>(context, 0) {

        override fun getCount(): Int = messages.size

        override fun getItem(position: Int): String? = messages[position]

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater: LayoutInflater = this@ChatWindow.layoutInflater
            val result: View = if (position % 2 == 0) {
                inflater.inflate(R.layout.chat_row_incoming, parent, false)
            } else {
                inflater.inflate(R.layout.chat_row_outgoing, parent, false)
            }

            val messageText = result.findViewById<TextView>(R.id.message_text)
            messageText.text = getItem(position)

            return result
        }
    }
}
