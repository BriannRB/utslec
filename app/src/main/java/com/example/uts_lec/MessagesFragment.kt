package com.example.uts_lec

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MessagesFragment : Fragment(R.layout.fragment_messages) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengatur RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_messages)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Data dummy untuk ditampilkan
        val messages = listOf(
            Message("Alice", "Hello! How are you?"),
            Message("Bob", "Let's meet tomorrow"),
            Message("Charlie", "Check your email!")
        )

        // Mengatur adapter dengan data
        recyclerView.adapter = MessageAdapter(messages)

        // Fungsi tombol kembali
        val backButton = view.findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }
}
