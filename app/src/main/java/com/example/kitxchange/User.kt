package com.example.kitxchange

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: Int = 0,
    val username: String,
    val avatarUri: String? = null,
    val xmrAddress: String = "",
    val favTeam: String = "",
    val showLocation: Boolean = false
)
