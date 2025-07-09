package com.devdroid.shoesapp.activity

import android.content.Intent
import android.os.Bundle
import com.devdroid.shoesapp.databinding.ActivityIntroBinding
import com.google.firebase.auth.FirebaseAuth

class introActivity : BaseActivity() {
    protected lateinit var binding:ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startbtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        binding.textView3.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
}