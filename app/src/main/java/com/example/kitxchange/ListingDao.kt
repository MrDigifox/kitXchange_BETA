package com.example.kitxchange

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ListingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(listing: Listing)

    @Query("SELECT * FROM listing ORDER BY id DESC")
    suspend fun getAll(): List<Listing>
}
