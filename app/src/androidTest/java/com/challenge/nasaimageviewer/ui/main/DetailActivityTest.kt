package com.challenge.nasaimageviewer.ui.main

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.challenge.nasaimageviewer.core.Constants
import com.challenge.nasaimageviewer.data.entities.NasaImageData

import com.challenge.nasaimageviewer.databinding.ActivityDetailBinding
import org.junit.Assert.*
import org.junit.Before

class DetailActivityTest{
    private lateinit var scenario:ActivityScenario<DetailActivity>

    @Before
    fun setup(){
        val imageData:NasaImageData = NasaImageData(1,"test_copyright","29-3-2022","explaination",
            "https://apod.nasa.gov/apod/image/0012/circinus_hst_big.jpg","image","v1","title","https://apod.nasa.gov/apod/image/0012/circinus_hst.jpg")

        val intent = Intent(ApplicationProvider.getApplicationContext(), DetailActivity::class.java).apply {
            putExtra(Constants.FRAGMENT_KEY, imageData)
        }
        scenario = ActivityScenario.launch(intent)

    }
}