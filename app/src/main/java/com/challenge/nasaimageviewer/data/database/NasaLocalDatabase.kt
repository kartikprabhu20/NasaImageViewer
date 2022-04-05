package com.challenge.nasaimageviewer.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.challenge.nasaimageviewer.core.Constants.DATABASE_NAME
import com.challenge.nasaimageviewer.data.entities.NasaImageData
import com.challenge.nasaimageviewer.data.convertors.NasaImageConvertors

/**
 * Databe for the project
 */
@Database(
    entities = arrayOf(NasaImageData::class),
    version = 1,
    exportSchema = false
)
@TypeConverters(NasaImageConvertors::class)
abstract class NasaLocalDatabase: RoomDatabase() {
    abstract fun nasaImageDao(): NasaImageDao

    companion object {
        @Volatile private var INSTANCE: NasaLocalDatabase? = null

        fun getDatabase(context: Context): NasaLocalDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NasaLocalDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}