package com.example.kitxchange

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: Int = 0,          //  always one row (id = 0)
    val username: String,
    val avatarUri: String? = null,        //  NEW – phone’s content-URI of the chosen photo
    val xmrAddress: String = ""           //  NEW – wallet address the user types
)
