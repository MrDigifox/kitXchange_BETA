package com.example.kitxchange

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Draw edge-to-edge (optional, keep if you like)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView(R.layout.activity_login)   // ← NO “.xml” extension
    }
}
