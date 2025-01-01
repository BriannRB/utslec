package com.example.uts_lec

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class DMPage : ComponentActivity() {

    private lateinit var commentRecyclerView: RecyclerView
    private lateinit var commentInput: EditText
    private lateinit var sendButton: Button
    private val commentsList = mutableListOf<Pair<String, String>>() // List untuk menyimpan pesan dan timestamp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dm_page) // Pastikan ini sesuai dengan nama layout XML Anda

        commentRecyclerView = findViewById(R.id.messageListView)
        commentInput = findViewById(R.id.messageInput)
        sendButton = findViewById(R.id.sendButton)

        // Setup RecyclerView
        commentRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CommentAdapter(commentsList)
        commentRecyclerView.adapter = adapter

        // Aksi tombol kirim
        sendButton.setOnClickListener {
            val comment = commentInput.text.toString().trim()
            if (comment.isNotEmpty()) {
                val timestamp = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
                commentsList.add(Pair(comment, timestamp)) // Menyimpan pesan dan timestamp
                adapter.notifyItemInserted(commentsList.size - 1) // Notifikasi adapter
                commentInput.text.clear() // Kosongkan input
                commentRecyclerView.scrollToPosition(commentsList.size - 1) // Scroll ke komentar terbaru
            } else {
                Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
