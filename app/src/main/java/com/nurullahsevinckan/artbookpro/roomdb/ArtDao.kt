package com.nurullahsevinckan.artbookpro.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArtDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // if there is a conflict with insertion then replace with new data
    suspend fun insertArt(art : Art)

    @Delete
    suspend fun deleteArt(art : Art)

    @Query("SELECT * FROM arts")
    fun observeArts() : LiveData<List<Art>> // livedata works async so we do not have to put suspend tag.

}