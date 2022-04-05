package com.challenge.nasaimageviewer.util

import com.challenge.nasaimageviewer.data.entities.NasaImageData

interface CustomClickListener {
    fun onItemClicked(imageData: NasaImageData)
}