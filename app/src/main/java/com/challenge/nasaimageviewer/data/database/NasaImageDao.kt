package com.challenge.nasaimageviewer.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.nasaimageviewer.data.entities.NasaImageData

/**
 * Data access object for nasa information
 */
@Dao
interface NasaImageDao {
    @Query("SELECT * FROM nasa_table")
    suspend fun getNasaPhotos(): List<NasaImageData>

    @Query("SELECT * FROM nasa_table where id = :id")
    suspend fun getNasaPhoto(id: Int): NasaImageData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(nasaImage: NasaImageData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(nasaImage: List<NasaImageData>)

    @Query("DELETE FROM nasa_table")
    suspend fun deleteAll()
}