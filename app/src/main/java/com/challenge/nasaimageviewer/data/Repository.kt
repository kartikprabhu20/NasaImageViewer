package com.challenge.nasaimageviewer.data

import com.challenge.nasaimageviewer.data.entities.NasaImageData

interface Repository {
    suspend fun fetchRemoteNasaData()
    suspend fun getAllNasaData(): List<NasaImageData>
    suspend fun getNasaData(id:Int): NasaImageData
}