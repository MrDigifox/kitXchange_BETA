package com.example.kitxchange

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "listing")
data class Listing(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val priceXmr: Double,
    val description: String,
    val imageUri: String? = null
)
