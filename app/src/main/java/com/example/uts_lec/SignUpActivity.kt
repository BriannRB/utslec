package com.example.uts_lec

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.util.Log
import java.util.*

data class User(
    var name: String? = "",
    var birthday: String? = "",
    var email: String? = "",
    var profileImageUrl: String? = ""
) {
    // Constructor tanpa argumen
    constructor() : this("", "", "")
}

class SignUpActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().getReference("users")

        val nameInput: EditText = findViewById(R.id.nameInput)
        val emailInput: EditText = findViewById(R.id.emailInput)
        val passwordInput: EditText = findViewById(R.id.passwordInput)
        val birthdayInput: EditText = findViewById(R.id.birthdayInput)

        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener { finish() }

        val signUpButton: Button = findViewById(R.id.signUpButton)
        signUpButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val birthday = birthdayInput.text.toString().trim()

            // Validasi input
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || birthday.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!email.endsWith("@student.umn.ac.id")) {
                Toast.makeText(this, "Hanya mahasiswa UMN yang dapat mendaftar!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Tambahkan log untuk debugging
            Log.d("SignUpActivity", "Attempting to sign up user: $email")

            // Proses pendaftaran
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid
                        Log.d("SignUpActivity", "User ID: $userId")
                        val user = User(name = name, email = email, birthday = birthday)

                        if (userId != null) {
                            databaseRef.child(userId).setValue(user)
                                .addOnCompleteListener { dbTask ->
                                    if (dbTask.isSuccessful) {
                                        Toast.makeText(this, "Sign Up berhasil", Toast.LENGTH_SHORT).show()
                                        Log.d("SignUpActivity", "Navigating to SignInActivity")
                                        // Langsung navigasi ke SignInActivity
                                        startActivity(Intent(this, SignInActivity::class.java))
                                        finish() // Tutup SignUpActivity
                                    } else {
                                        Toast.makeText(this, "Gagal menyimpan data pengguna", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        }
                    } else {
                        Toast.makeText(this, "Sign Up gagal: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        birthdayInput.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                birthdayInput.setText(selectedDate)
            }, year, month, day)

            datePickerDialog.show()
        }

        passwordInput.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (passwordInput.right - passwordInput.compoundDrawables[2].bounds.width())) {
                    if (isPasswordVisible) {
                        passwordInput.transformationMethod = PasswordTransformationMethod.getInstance()
                        passwordInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0)
                        isPasswordVisible = false
                    } else {
                        passwordInput.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        passwordInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_off, 0)
                        isPasswordVisible = true
                    }
                    passwordInput.setSelection(passwordInput.text.length)
                    return@setOnTouchListener true
                }
            }
            false
        }
    }
}