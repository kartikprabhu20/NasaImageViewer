package com.challenge.nasaimageviewer.data.database

import androidx.lifecycle.LiveData
import com.challenge.nasaimageviewer.data.entities.NasaImageData

interface RemoteDataSource {
    val downloadedNasaData: LiveData<List<NasaImageData>>
    suspend fun getRemoteNasaData()
}