package com.devdroid.shoesapp.activity

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.TextView
import com.devdroid.shoesapp.R


class SplashScreenActivity : BaseActivity() {

    private lateinit var soleSyncTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, introActivity::class.java))
            finish()
        }, 5500)

        soleSyncTextView = findViewById(R.id.textView8);

        // Initially hide the TextView
        soleSyncTextView.setVisibility(View.INVISIBLE);

        android.os.Handler().postDelayed(Runnable { // Show the TextView after 50ms
            soleSyncTextView.setVisibility(View.VISIBLE)
        }, 500)

    }
}