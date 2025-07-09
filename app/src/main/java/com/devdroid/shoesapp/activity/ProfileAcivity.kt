package com.devdroid.shoesapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
// No longer need View for managing ProgressBar visibility as per new XML
import com.devdroid.shoesapp.databinding.ActivityProfileAcivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileAcivity : BaseActivity() {

    private lateinit var binding: ActivityProfileAcivityBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileAcivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser

        if (currentUser != null) {
            val uid = currentUser.uid
            val email = currentUser.email ?: "N/A"

            binding.profileEmail.text = "Email: $email"

            firestore.collection("Users").document(uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val name = document.getString("name") ?: "N/A"
                        val username = document.getString("username") ?: "N/A"

                        binding.profileName.text = "Name: $name"
                        binding.profileUsername.text = "Username: $username"
                    } else {
                        Toast.makeText(this, "User data not found in database.", Toast.LENGTH_SHORT).show()

                        binding.profileName.text = "Name: N/A (Data Missing)"
                        binding.profileUsername.text = "Username: N/A (Data Missing)"
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error fetching user data: ${e.message}", Toast.LENGTH_LONG).show()

                    binding.profileName.text = "Name: Not Found"
                    binding.profileUsername.text = "Username: Not Found"
                }
        } else {
            Toast.makeText(this, "You are not logged in. Please log in.", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, AccountActivity::class.java))
            finish()
        }

        binding.logoutButton.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Logged out successfully!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AccountActivity::class.java))
            finish()
        }
    }
}