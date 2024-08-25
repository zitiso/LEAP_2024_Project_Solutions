package com.example.leapweather.data

data class WeatherResponse(
    val current: CurrentWeather
)

data class CurrentWeather(
    val temperature_2m: Double,
    val relative_humidity_2m: Double,
    val apparent_temperature: Double,
    val precipitation: Double,
    val wind_speed_10m: Double,
    val wind_direction_10m: Int
)