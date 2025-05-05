package com.example.kitxchange
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        findViewById<Button>(R.id.btnBrowse).setOnClickListener {
            startActivity(Intent(this, BrowseActivity::class.java))
        }
        findViewById<Button>(R.id.btnCreate).setOnClickListener {
            startActivity(Intent(this, CreateListingActivity::class.java))
        }
        findViewById<Button>(R.id.btnProfile).setOnClickListener {
            Toast.makeText(this, "Profile coming soon", Toast.LENGTH_SHORT).show()
        }
    }
}