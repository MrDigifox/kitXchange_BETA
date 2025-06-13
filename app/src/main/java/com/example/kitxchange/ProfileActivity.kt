package com.example.kitxchange

import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class ProfileActivity : AppCompatActivity() {

    private val db by lazy { AppDatabase.get(this) }
    private lateinit var user: User

    // image picker
    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { saveAvatar(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        lifecycleScope.launch { loadUser() }                    //loads user information

        findViewById<Button>(R.id.btnChangeAvatar).setOnClickListener {
            pickImage.launch("image/*")
        }

        findViewById<Switch>(R.id.swLocation).setOnCheckedChangeListener { _, checked ->
            lifecycleScope.launch { db.userDao().upsert(user.copy(showLocation = checked)) }
        }
    }


    private suspend fun loadUser() {                                /** Fetch user from Room and populate UI */
        user = db.userDao().get() ?: return
        withContext(Dispatchers.Main) {
            tv(R.id.tvUsername).text = user.username
            tv(R.id.tvTeam).text     = "Team: ${user.favTeam}"
            findViewById<Switch>(R.id.swLocation).isChecked = user.showLocation
            user.avatarUri?.let {
                findViewById<ImageView>(R.id.imgAvatar).setImageURI(Uri.parse(it))
            }
            tv(R.id.tvBalance).text = "Balance: 12.34 XMR"
        }
    }

    private fun saveAvatar(uri: Uri) = lifecycleScope.launch {
        val dest = File(filesDir, "avatar.jpg")
        contentResolver.openInputStream(uri)?.use { input ->
            dest.outputStream().use { input.copyTo(it) }
        }
        user = user.copy(avatarUri = dest.toURI().toString())
        db.userDao().upsert(user)
        withContext(Dispatchers.Main) {
            findViewById<ImageView>(R.id.imgAvatar).setImageURI(Uri.fromFile(dest))
        }
    }

    private fun tv(id: Int) = findViewById<TextView>(id)
}
