package com.example.leapweather.data

data class CurrentWeatherInfo(
    var city: String,
    var state: String,
    var temperature: Double,
    var feels_like_temperature: Double,
    var relative_humidity: Double,
    var precipitation: Double,
    var wind_speed: Double,
    var wind_direction: String,
) {
    companion object {
        fun empty(): CurrentWeatherInfo {
            return CurrentWeatherInfo(
                city = "",
                state = "",
                temperature = 0.0,
                feels_like_temperature = 0.0,
                relative_humidity = 0.0,
                precipitation = 0.0,
                wind_speed = 0.0,
                wind_direction = ""
            )
        }
    }
}
