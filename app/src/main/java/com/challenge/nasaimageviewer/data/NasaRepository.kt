package com.challenge.nasaimageviewer.data

import androidx.lifecycle.MutableLiveData
import com.challenge.nasaimageviewer.data.database.NasaImageDao
import com.challenge.nasaimageviewer.data.database.RemoteDataSource
import com.challenge.nasaimageviewer.data.entities.NasaImageData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * An abstraction between local and remote databases
 */
class NasaRepository(private val nasaImageDao: NasaImageDao,
                     val remoteDataSource: RemoteDataSource,
                     private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default) : Repository{

    var dbUpdated: MutableLiveData<Long> = MutableLiveData(Long.MIN_VALUE)

    companion object {
        private val TAG = NasaRepository::class.java.name
        const val GENERAL_ERROR_CODE = 499
    }

    override suspend fun fetchRemoteNasaData() {
        withContext(Dispatchers.IO) {
            remoteDataSource.getRemoteNasaData()
        }
    }

    suspend fun persistFetchedData(newdataList: List<NasaImageData>) = withContext(Dispatchers.IO) {
        nasaImageDao.insertAll(newdataList)
        dbUpdated.postValue(dbUpdated.value?.plus(1))
    }

    override suspend fun getAllNasaData() : List<NasaImageData> {
        return nasaImageDao.getNasaPhotos()
    }

    override suspend fun getNasaData(id: Int) : NasaImageData {
        return nasaImageDao.getNasaPhoto(id)
    }
}