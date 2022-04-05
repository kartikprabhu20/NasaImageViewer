package com.challenge.nasaimageviewer

import android.app.Application
import com.challenge.nasaimageviewer.data.NasaRepository
import com.challenge.nasaimageviewer.data.NasaService
import com.challenge.nasaimageviewer.data.database.NasaLocalDatabase
import com.challenge.nasaimageviewer.data.database.NasaRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NasaApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { NasaLocalDatabase.getDatabase(this) }
    val nasaService by lazy{ NasaService.getInstance()}
    val nasaRemoteDataSource by lazy{NasaRemoteDataSource(nasaService)}
    val repository by lazy { NasaRepository(database.nasaImageDao(),nasaRemoteDataSource) }
}