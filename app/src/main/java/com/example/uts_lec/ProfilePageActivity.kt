package com.example.uts_lec

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProfilePageActivity : AppCompatActivity() {

    private lateinit var username: TextView
    private lateinit var profileImage: ImageView
    private lateinit var editProfileButton: TextView

    private lateinit var databaseRef: DatabaseReference
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_profile_page)

        username = findViewById(R.id.username)
        profileImage = findViewById(R.id.profileImage)
        editProfileButton = findViewById(R.id.editProfile)
        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener { finish() }

        databaseRef = FirebaseDatabase.getInstance().getReference("users")
        auth = FirebaseAuth.getInstance()

        loadUserData()

        editProfileButton.setOnClickListener {
            val intent = Intent(this, EditAccountActivity::class.java)
            startActivityForResult(intent, 1) // Menggunakan startActivityForResult
        }
    }

    private fun loadUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            databaseRef.child(userId).get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    val user = dataSnapshot.getValue(User::class.java)
                    user?.let {
                        username.text = it.name
                        // Load profile image if available
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            loadUserData() // Memuat ulang data pengguna setelah kembali dari EditAccountActivity
        }
    }
}