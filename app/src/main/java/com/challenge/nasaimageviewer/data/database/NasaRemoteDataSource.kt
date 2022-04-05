package com.challenge.nasaimageviewer.data.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.nasaimageviewer.core.Constants
import com.challenge.nasaimageviewer.data.entities.NasaImageData
import com.challenge.nasaimageviewer.data.NasaService
import retrofit2.await
import java.io.IOException

/**
 * Remote datasorurce from which we fetch data using nasa api
 */
class NasaRemoteDataSource(val nasaService: NasaService) : RemoteDataSource{

    private val _downloadedNasaData = MutableLiveData<List<NasaImageData>>() // can be changed
    override val downloadedNasaData: LiveData<List<NasaImageData>>
        get() = _downloadedNasaData

    override suspend fun getRemoteNasaData() {

        try {
            val fetchedphotoData = nasaService
                .getNasaInfo(Constants.API_KEY,
                    100) //100 is the limit for the api
                ?.await()//

            _downloadedNasaData.postValue(fetchedphotoData!!)
        } catch (exception: IOException){
            Log.e("NasaRemoteDataSource", exception.toString());
        }
    }
}

