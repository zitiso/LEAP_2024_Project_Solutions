package com.example.starwars.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.data.Planet
import com.example.starwars.data.PlanetWrapper
import com.example.starwars.services.PlanetService
import kotlinx.coroutines.launch

class PlanetViewModel : ViewModel() {

    private val _planetWrapper : MutableState<PlanetWrapper> =
        mutableStateOf(PlanetWrapper(0,null,null,emptyList<Planet>()))

    val planetWrapper get() = _planetWrapper

    fun getPlanets() {
        viewModelScope.launch {
            val planetService = PlanetService.getInstance()
            try {
                _planetWrapper.value = planetService.getPlanets()
            } catch (e: Exception) {

            }
        }
    }


}