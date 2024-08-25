package com.example.leapweather.services

import com.example.leapweather.data.Location
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LocationService {

    @GET("search")
    suspend fun getLocation(
        @Header("apikey") apikey: String = "01J1Z5D1V7GBJAQEDRBX7H271B",
        @Query("codes") zipcode: String,
        @Query("country") country: String = "us"
    ): Location

    companion object {
        val BASE_URL = "https://api.zipcodestack.com/v1/"

        var locationService: LocationService? = null

        fun getInstance(): LocationService {

            if (locationService == null) {
                locationService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(LocationService::class.java)
            }

            return locationService!!

        }
    }

}