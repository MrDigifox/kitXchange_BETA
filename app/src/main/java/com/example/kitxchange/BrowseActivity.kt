package com.example.kitxchange

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class BrowseActivity : AppCompatActivity() {

    private val db by lazy { AppDatabase.get(this) }
    private lateinit var listView: ListView
    private lateinit var adapter: ListingAdapter
    private var currentRows: List<Listing> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)

        listView = findViewById(R.id.lvListings)
        adapter = ListingAdapter(this, emptyList())
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, pos, _ ->
            currentRows.getOrNull(pos)?.let { listing ->
                AlertDialog.Builder(this)
                    .setTitle(listing.title)
                    .setMessage("""
                        Price: ${listing.priceXmr} XMR
                        
                        ${listing.description.ifBlank { "(no description)" }}
                    """.trimIndent())
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            currentRows = db.listingDao().getAll()
            if (currentRows.isEmpty()) {
                adapter.update(listOf(Listing(title = "No listings yet.", priceXmr = 0.0, description = "")))
            } else {
                adapter.update(currentRows)
            }
        }
    }
}
