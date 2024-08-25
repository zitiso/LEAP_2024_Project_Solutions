package com.example.leapweather.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leapweather.viewmodel.CurrentWeatherViewModel

@Composable
fun WeatherPage(currentWeatherViewModel: CurrentWeatherViewModel) {

    val weatherInfo by currentWeatherViewModel.currentWeatherInfo.observeAsState()

    if (weatherInfo != null) {

        val commonTextStyle = TextStyle(fontSize = 14.sp)

        Column(modifier = Modifier.padding(20.dp)) {

            Text(
                text = "Current Weather",
                modifier = Modifier.padding(top = 10.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            weatherInfo?.let {
                Text(
                    text = """${it.city}, ${it.state}""",
                    style = commonTextStyle,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                Text(
                    text = """Temperature: ${it.temperature} °F (feels like ${it.feels_like_temperature} °F)""",
                    style = commonTextStyle
                )

                Text(
                    text = """Relative humidity: ${it.relative_humidity}%""",
                    style = commonTextStyle
                )

                Text(
                    text = """Precipitation: ${it.precipitation} inches""",
                    style = commonTextStyle
                )

                Text(
                    text = """Wind: ${it.wind_direction} ${it.wind_speed} mph""",
                    style = commonTextStyle
                )
            }
        }
    }
}