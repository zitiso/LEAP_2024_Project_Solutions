package com.example.starwars.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.data.PeopleWrapper
import com.example.starwars.data.Person
import com.example.starwars.services.PeopleService
import kotlinx.coroutines.launch

class PeopleViewModel : ViewModel() {

    private val _peopleWrapper :MutableState<PeopleWrapper> =
        mutableStateOf(PeopleWrapper(0,null,null,emptyList<Person>()))

    val peopleWrapper get() = _peopleWrapper

    fun getPeople() {
        viewModelScope.launch {
            val peopleService = PeopleService.getInstance()
            try {
                _peopleWrapper.value = peopleService.getPeople()
            } catch (e: Exception) {

            }
        }
    }

}