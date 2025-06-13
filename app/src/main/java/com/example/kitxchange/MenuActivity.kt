package com.example.kitxchange

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuActivity : AppCompatActivity() {

    private lateinit var tvXmr: TextView
    private val db by lazy { AppDatabase.get(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // ----- live XMR label -----
        tvXmr = findViewById(R.id.tvXmrPrice)
        refreshXmrPrice()

        // ----- navigation buttons -----
        findViewById<Button>(R.id.btnBrowse).setOnClickListener {
            startActivity(Intent(this, BrowseActivity::class.java))
        }

        findViewById<Button>(R.id.btnCreate).setOnClickListener {
            startActivity(Intent(this, CreateListingActivity::class.java))
        }

        // *** FIXED: open ProfileActivity instead of Toast ***
        findViewById<Button>(R.id.btnProfile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    /** Fetches live Monero price via Retrofit */
    private fun refreshXmrPrice() = lifecycleScope.launch {
        tvXmr.text = "XMR: …"
        try {
            val price = withContext(Dispatchers.IO) { Net.api.getXmrPrice().monero.usd }
            tvXmr.text = "XMR: $%.2f".format(price)
        } catch (e: Exception) {
            tvXmr.text = "XMR: N/A"
            Toast.makeText(this@MenuActivity,
                "Couldn't fetch XMR price", Toast.LENGTH_SHORT).show()
        }
    }
}
