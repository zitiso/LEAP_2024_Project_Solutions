package com.example.starwars.people.services

import com.example.starwars.people.StarWarsConstants
import com.example.starwars.people.data.Film
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmService {

    @GET("films/{id}")
    suspend fun getFilm(@Path("id") id :Int) : Film

    companion object {
        var filmService :FilmService ?= null
        fun getInstance() :FilmService {
            if( filmService == null ) {
                filmService = Retrofit.Builder()
                    .baseUrl(StarWarsConstants.SWAPI_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(FilmService::class.java)
            }
            return filmService!!
        }
    }

}