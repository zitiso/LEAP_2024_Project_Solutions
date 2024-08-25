package com.example.leapweather.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.leapweather.viewmodel.CurrentWeatherViewModel

@Composable
fun InputPage(currentWeatherViewModel: CurrentWeatherViewModel) {

    var zipcode by remember { mutableStateOf("") }

    Column {
        Row(
            modifier = Modifier
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TextField(
                value = zipcode,
                onValueChange = { zipcode = it },
                label = { Text(text = "Zip Code") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth(0.6f)
                    .height(60.dp)
            )
            Button(
                enabled = true,
                onClick = {
//                    sharedViewModel.zipcodeState.value = zipcode
                    currentWeatherViewModel.getCurrentWeather(zipcode)
                },
                colors = ButtonDefaults.buttonColors(),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
            ) {
                Text("Submit")
            }

        }

    }
}

