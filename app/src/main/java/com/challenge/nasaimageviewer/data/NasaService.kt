package com.challenge.nasaimageviewer.data

import com.challenge.nasaimageviewer.core.Constants.BASE_URL
import com.challenge.nasaimageviewer.data.entities.NasaImageData
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service to hit the endpoint
 */
interface NasaService {

    @GET("planetary/apod")
    fun getNasaInfo(
        @Query("api_key") apiKey: String,
        @Query("count") count: Int,
        ): Call<List<NasaImageData>>?

    companion object{
        var nasaService: NasaService? = null

        fun getInstance(): NasaService {

            val okHttpClient = OkHttpClient
                .Builder()
                .build()

            if (nasaService == null) {
                nasaService = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(NasaService::class.java)
            }
            return nasaService!!
        }
    }

}