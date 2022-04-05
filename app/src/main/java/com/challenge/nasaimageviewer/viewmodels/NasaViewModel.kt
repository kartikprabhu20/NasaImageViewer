package com.challenge.nasaimageviewer.viewmodels

import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.*
import com.challenge.nasaimageviewer.data.NasaRepository
import com.challenge.nasaimageviewer.data.entities.NasaImageData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.*


class NasaViewModel constructor(private val repository: NasaRepository)  : ViewModel() {

    var mElapsedTime: MutableLiveData<Long> = MutableLiveData(Long.MIN_VALUE)
    val timer = Timer()
    var mInitialTime = SystemClock.elapsedRealtime()

    private var _nasaDataList = MutableLiveData<List<NasaImageData>>()
    val nasaDataList: LiveData<List<NasaImageData>> = _nasaDataList

    init{
        fetchRemoteData()
//        startTimer()

        viewModelScope.launch {
            getTimer().collect {
                mElapsedTime.postValue(mElapsedTime.value?.plus(1L))
            }

        }

        repository.remoteDataSource.downloadedNasaData.observeForever{newdata ->
            viewModelScope.launch { repository.persistFetchedData(newdata)}
        }

        repository.dbUpdated.observeForever{
            viewModelScope.launch {
                _nasaDataList.value = repository.getAllNasaData()
            }
        }
    }

    fun startTimer(){
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue: Long = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                // setValue() cannot be called from a background thread so postValue is used.
                mElapsedTime.postValue(newValue)
                Log.v("kartik","scheduleAtFixedRate")
            }
        }, 10000, 10000)
    }

    fun getTimer() = flow {
        while (true) {
            emit(Unit)
            delay(10000L)
        }
    }


    fun fetchRemoteData() = viewModelScope.launch {
        repository.fetchRemoteNasaData()
    }

}