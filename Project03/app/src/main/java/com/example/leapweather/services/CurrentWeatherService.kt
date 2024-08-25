package com.example.leapweather.services

import android.util.Log
import com.example.leapweather.data.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherService {
    @GET("forecast")
    suspend fun getCurrentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = "temperature_2m,relative_humidity_2m,apparent_temperature,precipitation,wind_speed_10m,wind_direction_10m",
        @Query("hourly") hourly: String = "temperature_2m",
        @Query("temperature_unit") temperatureUnit: String = "fahrenheit",
        @Query("wind_speed_unit") windSpeedUnit: String = "mph",
        @Query("precipitation_unit") precipitationUnit: String = "inch",
        @Query("timezone") timezone: String = "America/Chicago",
        @Query("forecast_days") forecastDays: Int = 3
    ): WeatherResponse

    companion object {
        val BASE_URL = "https://api.open-meteo.com/v1/"

        var currentWeatherService: CurrentWeatherService? = null

        fun getInstance(): CurrentWeatherService {

            if (currentWeatherService == null) {
                currentWeatherService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(CurrentWeatherService::class.java)
            }

            Log.wtf("ForecastService", """${currentWeatherService}""")

            return currentWeatherService!!

        }
    }


}
