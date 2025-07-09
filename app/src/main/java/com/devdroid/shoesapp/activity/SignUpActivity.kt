package com.devdroid.shoesapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns // Import for email validation
import android.view.View // Import for managing view visibility (e.g., progress bar)
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devdroid.shoesapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException // Import for specific Firebase error
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        binding.startbtn.setOnClickListener {
            signUpUser()
        }

        // CORRECTED: Redirect to LoginActivity
        binding.LoginRedirectText.setOnClickListener {
            startActivity(Intent(this, AccountActivity::class.java)) // Assuming you have a LoginActivity
        }
    }

    private fun signUpUser() {
        val name = binding.signupName.text.toString().trim()
        val username = binding.signupUsername.text.toString().trim()
        val email = binding.signupEmail.text.toString().trim()
        val password = binding.signupPassword.text.toString().trim()
        val confirmPassword = binding.signupConfirmPassword.text.toString().trim()

        // 1. Basic Field Validation
        if (name.isEmpty()) {
            binding.signupName.error = "Name is required"
            binding.signupName.requestFocus()
            return
        }
        if (username.isEmpty()) {
            binding.signupUsername.error = "Username is required"
            binding.signupUsername.requestFocus()
            return
        }
        if (email.isEmpty()) {
            binding.signupEmail.error = "Email is required"
            binding.signupEmail.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.signupEmail.error = "Please enter a valid email"
            binding.signupEmail.requestFocus()
            return
        }
        if (password.isEmpty()) {
            binding.signupPassword.error = "Password is required"
            binding.signupPassword.requestFocus()
            return
        }
        // Basic password length check (Firebase requires at least 6 characters)
        if (password.length < 6) {
            binding.signupPassword.error = "Password should be at least 6 characters"
            binding.signupPassword.requestFocus()
            return
        }
        if (confirmPassword.isEmpty()) {
            binding.signupConfirmPassword.error = "Confirm password is required"
            binding.signupConfirmPassword.requestFocus()
            return
        }

        if (password != confirmPassword) {
            binding.signupConfirmPassword.error = "Passwords do not match"
            binding.signupConfirmPassword.requestFocus()
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        // Show a loading indicator (assuming you have a ProgressBar in your layout with id `progressBar`)
        // You would need to add a ProgressBar to your ActivitySignUpBinding layout XML
        // e.g., <ProgressBar android:id="@+id/progressBar" android:layout_width="wrap_content"
        // android:layout_height="wrap_content" android:visibility="gone"/>
        binding.progressBar.visibility = View.VISIBLE
        binding.startbtn.isEnabled = false // Disable button to prevent multiple clicks

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                // Hide loading indicator
                binding.progressBar.visibility = View.GONE
                binding.startbtn.isEnabled = true

                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid
                    val userMap = hashMapOf(
                        "name" to name,
                        "username" to username,
                        "email" to email,
                        "uid" to uid // Often good to store uid in the document as well
                    )

                    if (uid != null) {
                        firestore.collection("Users").document(uid)
                            .set(userMap)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Sign up successful!", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, AccountActivity::class.java))
                                finish() // Finish SignUpActivity so user can't go back
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Error saving user data: ${e.message}", Toast.LENGTH_LONG).show()
                            }
                    } else {
                        // This case should ideally not happen if task.isSuccessful is true
                        Toast.makeText(this, "Sign up failed: User UID not found.", Toast.LENGTH_LONG).show()
                    }
                } else {
                    // Specific error handling for Firebase Auth
                    if (task.exception is FirebaseAuthUserCollisionException) {
                        Toast.makeText(this, "Sign up failed: This email is already registered.", Toast.LENGTH_LONG).show()
                        binding.signupEmail.error = "Email already registered"
                        binding.signupEmail.requestFocus()
                    } else {
                        Toast.makeText(this, "Sign up failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
    }
}