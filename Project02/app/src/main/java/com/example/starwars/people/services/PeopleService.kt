package com.example.starwars.people.services

import com.example.starwars.people.StarWarsConstants.SWAPI_BASE_URL
import com.example.starwars.people.data.PeopleWrapper
import com.example.starwars.people.data.Person
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleService {

    @GET("people")
    suspend fun getPeople(@Query("page") page :Int) : PeopleWrapper

    @GET("people/{id}")
    suspend fun getPerson(@Path("id") id :Int) : Person

    companion object {
        var peopleService :PeopleService ?= null
        fun getInstance() :PeopleService {
            if( peopleService == null ) {
                peopleService = Retrofit.Builder()
                    .baseUrl(SWAPI_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PeopleService::class.java)
            }
            return peopleService!!
        }
    }
}