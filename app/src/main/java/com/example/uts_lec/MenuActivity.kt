package com.example.uts_lec

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MenuActivity : ComponentActivity() {

    private lateinit var postImageView: ImageView
    private lateinit var postCaptionView: TextView
    private lateinit var profileImage: ImageView
    private lateinit var databaseRef: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Inisialisasi elemen UI
        postImageView = findViewById(R.id.postImage)
        postCaptionView = findViewById(R.id.postCaption)
        profileImage = findViewById(R.id.overlay_image)

        databaseRef = FirebaseDatabase.getInstance().getReference("posts")
        auth = FirebaseAuth.getInstance()

        // Panggil loadUserData setelah inisialisasi elemen UI
        loadUserData()

        profileImage.setOnClickListener {
            val intent = Intent(this, ProfilePageActivity::class.java)
            startActivity(intent)
        }

        val everyoneButton: Button = findViewById(R.id.everyoneButton)
        everyoneButton.setOnClickListener {
            Toast.makeText(this, "Everyone button clicked!", Toast.LENGTH_SHORT).show()
        }

        // Listener untuk ikon pesan
        val messageIcon: View = findViewById(R.id.messageIcon)
        messageIcon.setOnClickListener {
            // Pindah ke DMPage
            val intent = Intent(this, DMPage::class.java)
            startActivity(intent)
        }

        val messageInput: EditText = findViewById(R.id.messageInput)
        val sendMessageIcon: View = findViewById(R.id.sendMessageIcon)

        sendMessageIcon.setOnClickListener {
            val message = messageInput.text.toString().trim()
            if (message.isNotEmpty()) {
                Toast.makeText(this, "Pesan terkirim: $message", Toast.LENGTH_SHORT).show()
                messageInput.text.clear()
            } else {
                Toast.makeText(this, "Pesan tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val userRef = FirebaseDatabase.getInstance().getReference("users").child(userId)
            userRef.get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    val user = dataSnapshot.getValue(User::class.java)
                    user?.let {
                        // Memuat gambar profil jika tersedia
                        if (it.profileImageUrl != null) {
                            Glide.with(this)
                                .load(it.profileImageUrl)
                                .apply(RequestOptions().transform(CircleCrop()))
                                .into(profileImage)
                        }
                    }
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Gagal memuat data pengguna", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
