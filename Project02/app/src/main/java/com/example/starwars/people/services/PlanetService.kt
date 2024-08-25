package com.example.starwars.people.services

import com.example.starwars.people.StarWarsConstants
import com.example.starwars.people.data.Planet
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetService {

    @GET("planets/{id}")
    suspend fun getPlanet(@Path("id") id :Int) : Planet

    companion object {
        var planetService :PlanetService ?= null
        fun getInstance() :PlanetService {
            if( planetService == null ) {
                planetService = Retrofit.Builder()
                    .baseUrl(StarWarsConstants.SWAPI_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PlanetService::class.java)
            }
            return planetService!!
        }
    }
}