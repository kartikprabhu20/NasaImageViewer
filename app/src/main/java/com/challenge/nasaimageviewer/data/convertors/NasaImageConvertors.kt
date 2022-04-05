package com.challenge.nasaimageviewer.data.convertors

import androidx.room.TypeConverter
import com.challenge.nasaimageviewer.data.entities.NasaImageData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NasaImageConvertors {
    @TypeConverter
    fun toNasaImageData(nasaInfo:String): NasaImageData {
        val type=object : TypeToken<NasaImageData>(){}.type
        return Gson().fromJson(nasaInfo,type)
    }
    @TypeConverter
    fun toNasaImageData(nasaInfo: NasaImageData):String{
        val type=object :TypeToken<NasaImageData>(){}.type
        return Gson().toJson(nasaInfo,type)
    }
}