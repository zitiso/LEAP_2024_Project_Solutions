package com.example.leapweather.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leapweather.data.CurrentWeatherInfo
import com.example.leapweather.data.WeatherResponse
import com.example.leapweather.data.ZipInfo
import com.example.leapweather.services.CurrentWeatherService
import com.example.leapweather.services.LocationService
import kotlinx.coroutines.launch
import kotlin.Exception

class CurrentWeatherViewModel : ViewModel() {

    private val _currentWeatherInfo = MutableLiveData<CurrentWeatherInfo?>()
    val currentWeatherInfo get() = _currentWeatherInfo

    var zipInfoCache :MutableMap<String,ZipInfo?> = mutableMapOf()

    fun getCurrentWeather(zipcode :String){

        Log.wtf("CurrentWeatherViewModel", """getCurrentWeather(${zipcode})""" )

        var zipInfo :ZipInfo? = ZipInfo.empty()

        viewModelScope.launch{

            if( zipInfoCache.containsKey(zipcode) ) {
                Log.wtf("CurrentWeatherViewModel", """Using Cache for zip (${zipcode})""" )
                // zipcode data is cached - retrieve from cache
                zipInfo = zipInfoCache[zipcode]

            } else {

                // zipcode data is NOT cached
                // retrieve from web service and store in cache
                val locationService = LocationService.getInstance()

                try{

                    val currentLocation = locationService.getLocation( zipcode = zipcode )
                    zipInfoCache.set(zipcode,currentLocation.results[zipcode]?.get(0))
                    zipInfo = currentLocation.results[zipcode]?.get(0)

                } catch (e : Exception) {
                    Log.wtf("CurrentWeatherViewModel", e.message )
                }

            }

            val currentWeatherService = CurrentWeatherService.getInstance()

            var weatherResponse :WeatherResponse? = null

            try{

                weatherResponse = currentWeatherService.getCurrentWeather(
                    latitude = zipInfo?.latitude ?: 0.0,
                    longitude = zipInfo?.longitude ?: 0.0,
                )

                Log.wtf("CurrentWeatherViewModel", weatherResponse.current.toString() )

            } catch (e : Exception) {
                Log.wtf("CurrentWeatherViewModel", e.message )
            }

            buildCurrentWeatherInfo(zipInfo,weatherResponse)

        }

    }

    private fun buildCurrentWeatherInfo( zipInfo: ZipInfo?, weatherResponse: WeatherResponse?  ) {

        if(zipInfo != null && weatherResponse !=null ) {

            val currentWeatherInfo = CurrentWeatherInfo.empty()

            currentWeatherInfo.city = zipInfo.city
            currentWeatherInfo.state = zipInfo.state

            currentWeatherInfo.temperature = weatherResponse.current.temperature_2m
            currentWeatherInfo.feels_like_temperature = weatherResponse.current.apparent_temperature
            currentWeatherInfo.relative_humidity = weatherResponse.current.relative_humidity_2m
            currentWeatherInfo.precipitation = weatherResponse.current.precipitation
            currentWeatherInfo.wind_speed = weatherResponse.current.wind_speed_10m
            currentWeatherInfo.wind_direction = degreesToCompass(weatherResponse.current.wind_direction_10m)

            _currentWeatherInfo.value = currentWeatherInfo

        }

    }

}

private fun degreesToCompass(degrees: Int): String {

    return when (degrees) {
        in 337..360, in 0..22 -> "N"
        in 23..67 -> "NE"
        in 68..112 -> "E"
        in 113..157 -> "SE"
        in 158..202 -> "S"
        in 203..247 -> "SW"
        in 248..292 -> "W"
        in 293..336 -> "NW"
        else -> "N/A"
    }
}



