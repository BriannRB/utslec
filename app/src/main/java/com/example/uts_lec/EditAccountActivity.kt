package com.example.uts_lec

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
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
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException

class EditAccountActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var birthdayTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var passwordInput: EditText
    private lateinit var confirmPasswordInput: EditText
    private lateinit var saveButton: Button
    private lateinit var backButton: ImageView
    private lateinit var profileImage: ImageView

    private lateinit var databaseRef: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var storageRef: StorageReference

    private val PICK_IMAGE_REQUEST = 1
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_account)

        nameInput = findViewById(R.id.nameInput)
        birthdayTextView = findViewById(R.id.birthdayTextView)
        emailTextView = findViewById(R.id.emailTextView)
        passwordInput = findViewById(R.id.passwordInput)
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput)
        saveButton = findViewById(R.id.saveButton)
        backButton = findViewById(R.id.backButton)
        profileImage = findViewById(R.id.profileImage)

        databaseRef = FirebaseDatabase.getInstance().getReference("users")
        auth = FirebaseAuth.getInstance()
        storageRef = FirebaseStorage.getInstance().reference

        loadUserData()

        profileImage.setOnClickListener {
            openGallery()
        }

        saveButton.setOnClickListener {
            saveChanges()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun loadUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            databaseRef.child(userId).get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    val user = dataSnapshot.getValue(User::class.java)
                    user?.let {
                        nameInput.setText(it.name)
                        birthdayTextView.text = it.birthday
                        emailTextView.text = it.email
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

    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            imageUri = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                profileImage.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun saveChanges() {
        val newName = nameInput.text.toString()
        val newPassword = passwordInput.text.toString()
        val confirmPassword = confirmPasswordInput.text.toString()

        if (newPassword.isNotEmpty() && newPassword != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        val userId = auth.currentUser?.uid
        if (userId != null) {
            databaseRef.child(userId).child("name").setValue(newName)

            if (imageUri != null) {
                val fileReference = storageRef.child("profile_images/$userId.jpg")
                fileReference.putFile(imageUri!!).addOnSuccessListener {
                    fileReference.downloadUrl.addOnSuccessListener { uri ->
                        // Update profileImageUrl in Realtime Database
                        databaseRef.child(userId).child("profileImageUrl").setValue(uri.toString())
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed to upload image", Toast.LENGTH_SHORT).show()
                }
            }

            if (newPassword.isNotEmpty()) {
                auth.currentUser?.updatePassword(newPassword)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed to update password", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}