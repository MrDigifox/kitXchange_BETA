package com.example.kitxchange

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class BrowseActivity : AppCompatActivity() {

    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)

        val listView = findViewById<ListView>(R.id.lvListings)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listView.adapter = adapter

        findViewById<View>(R.id.fabAddListing).setOnClickListener {
            startActivity(Intent(this, CreateListingActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.clear()
        adapter.addAll(
            ListingRepository.listings.map { "${it.title} – ${it.priceXmr} XMR" }
        )
        adapter.notifyDataSetChanged()
    }
}
