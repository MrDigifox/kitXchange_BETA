package com.example.kitxchange

import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class CreateListingActivity : AppCompatActivity() {

    private val db by lazy { AppDatabase.get(this) }

    // Hold the chosen image URI (single-photo MVP)
    private var selectedUri: Uri? = null

    // Image picker launcher
    private val pickImage = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedUri = it
            findViewById<ImageView>(R.id.imgPhotoPlaceholder).setImageURI(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_listing)

        val imgPlaceholder = findViewById<ImageView>(R.id.imgPhotoPlaceholder)
        val etTitle        = findViewById<EditText>(R.id.etTitle)
        val etPrice        = findViewById<EditText>(R.id.etPrice)
        val etDescription  = findViewById<EditText>(R.id.etDescription)
        val btnSubmit      = findViewById<Button>(R.id.btnSubmitListing)

        // Tap image to pick a photo
        imgPlaceholder.setOnClickListener { pickImage.launch("image/*") }

        btnSubmit.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val priceStr = etPrice.text.toString().trim()
            val desc  = etDescription.text.toString().trim()

            if (title.isEmpty() || priceStr.isEmpty()) {
                toast("Title and price required"); return@setOnClickListener
            }

            val price = priceStr.toDoubleOrNull()
            if (price == null) {
                toast("Invalid price"); return@setOnClickListener
            }

            lifecycleScope.launch {
                val listing = Listing(
                    title       = title,
                    priceXmr    = price,
                    description = desc,
                    imageUri    = selectedUri?.toString()
                )
                db.listingDao().insert(listing)
                toast("Listing saved!")
                finish()                // return to Browse
            }
        }
    }

    private fun toast(msg:String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
