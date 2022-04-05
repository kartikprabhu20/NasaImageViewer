package com.challenge.nasaimageviewer.viewmodels

import androidx.lifecycle.liveData
import com.challenge.nasaimageviewer.data.NasaRepository
import com.challenge.nasaimageviewer.data.NasaService
import com.challenge.nasaimageviewer.data.database.NasaImageDao
import com.challenge.nasaimageviewer.data.database.RemoteDataSource
import com.challenge.nasaimageviewer.data.entities.NasaImageData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class NasaViewModelTest{

    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var nasaViewModel: NasaViewModel
    lateinit var mainRepository: NasaRepository

    @Mock
    lateinit var nasaService: NasaService
    @Mock
    lateinit var nasaImageDao: NasaImageDao
    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mainRepository = NasaRepository(nasaImageDao,remoteDataSource)
        nasaViewModel = NasaViewModel(mainRepository)
    }

    @Test
    fun getNasaDataTest() {
        runBlocking {
            Mockito.`when`(nasaViewModel.getNasaData())
                .thenReturn(liveData {listOf<NasaImageData>(NasaImageData(1,"test_copyright","29-3-2022","explaination",
                    "https://test.png","image","v1","title","url"))})

            val result = nasaViewModel.getNasaData()
            assertEquals(listOf<NasaImageData>(NasaImageData(1,"test_copyright","29-3-2022","explaination",
                "https://test.png","image","v1","title","url")), result)
        }
    }
}