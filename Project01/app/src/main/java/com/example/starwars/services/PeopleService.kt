package com.example.starwars.services

import com.example.starwars.StarWarsConstants.BASE_URL
import com.example.starwars.data.PeopleWrapper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PeopleService {

    @GET("people")
    suspend fun getPeople() : PeopleWrapper

    companion object {
        var peopleService :PeopleService ?= null
        fun getInstance() :PeopleService {
            if( peopleService == null ) {
                peopleService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PeopleService::class.java)
            }
            return peopleService!!
        }
    }
}