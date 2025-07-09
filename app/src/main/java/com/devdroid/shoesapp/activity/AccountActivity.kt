package com.devdroid.shoesapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devdroid.shoesapp.databinding.ActivityAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using ViewBinding
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Authentication instance
        auth = FirebaseAuth.getInstance()

        // Set OnClickListener for the login button
        binding.loginbtn.setOnClickListener {
            loginUser()
        }

        // Set OnClickListener for the "Sign Up" redirect text
        binding.SignUpRedirectText.setOnClickListener {
            // Navigate to the SignUpActivity when the text is clicked
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    /**
     * Handles the user login process.
     * Performs input validation, shows a loading indicator,
     * attempts Firebase email/password sign-in, and handles success/failure.
     */
    private fun loginUser() {
        // Retrieve email and password from the EditText fields, trimming whitespace
        val email = binding.signupUsername.text.toString().trim() // This field is used for email in login
        val password = binding.signupPassword.text.toString().trim()

        // --- Input Validation ---
        if (email.isEmpty()) {
            binding.signupUsername.error = "Email is required"
            binding.signupUsername.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.signupUsername.error = "Please enter a valid email address"
            binding.signupUsername.requestFocus()
            return
        }
        if (password.isEmpty()) {
            binding.signupPassword.error = "Password is required"
            binding.signupPassword.requestFocus()
            return
        }

        // --- Show Loading Indicator and Disable Button ---
        binding.progressBar.visibility = View.VISIBLE
        binding.loginbtn.isEnabled = false // Disable the login button to prevent multiple clicks

        // --- Firebase Sign-In Attempt ---
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                // --- Hide Loading Indicator and Re-enable Button ---
                binding.progressBar.visibility = View.GONE
                binding.loginbtn.isEnabled = true

                if (task.isSuccessful) {
                    // Login successful
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

                    // Navigate to ProfileActivity
                    val intent = Intent(this, ProfileAcivity::class.java)
                    // Note: You generally don't need to pass email via intent here.
                    // In ProfileActivity, you can get the current user's email using FirebaseAuth.getInstance().currentUser?.email
                    startActivity(intent)
                    finish() // Finish the current activity (LoginActivity) so user can't go back to it
                } else {
                    // Login failed, handle specific Firebase exceptions for better user feedback
                    when (task.exception) {
                        is FirebaseAuthInvalidUserException -> {
                            // User does not exist or is disabled
                            Toast.makeText(this, "Login failed: User not found or disabled.", Toast.LENGTH_LONG).show()
                            binding.signupUsername.error = "User not registered"
                            binding.signupUsername.requestFocus()
                        }
                        is FirebaseAuthInvalidCredentialsException -> {
                            // Incorrect password for the given email
                            Toast.makeText(this, "Login failed: Incorrect password.", Toast.LENGTH_LONG).show()
                            binding.signupPassword.error = "Incorrect password"
                            binding.signupPassword.requestFocus()
                        }
                        else -> {
                            // General login failure
                            Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
    }
}