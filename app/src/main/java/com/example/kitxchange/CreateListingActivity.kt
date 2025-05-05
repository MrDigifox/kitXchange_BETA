package com.example.kitxchange
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateListingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_listing)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etPrice = findViewById<EditText>(R.id.etPrice)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val btnSubmit = findViewById<Button>(R.id.btnSubmitListing)

        btnSubmit.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val priceText = etPrice.text.toString().trim()
            val desc = etDescription.text.toString().trim()

            if (title.isEmpty() || priceText.isEmpty()) {
                Toast.makeText(this, "Title & Price required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val price = priceText.toDoubleOrNull()
            if (price == null) {
                Toast.makeText(this, "Invalid price", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            ListingRepository.listings.add(Listing(title, price, desc))
            Toast.makeText(this, "Listing added!", Toast.LENGTH_SHORT).show()
            finish()        // return to BrowseActivity
        }
    }
}