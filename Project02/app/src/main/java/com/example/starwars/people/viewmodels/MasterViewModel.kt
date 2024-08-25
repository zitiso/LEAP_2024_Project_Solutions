package com.example.starwars.people.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.people.data.Person
import com.example.starwars.people.services.PeopleService
import kotlinx.coroutines.launch


class MasterViewModel : ViewModel() {

    private val _people = MutableLiveData<List<Person>>(emptyList())
    val people get() = _people

    fun getPeople() {
        if(_people.value?.isEmpty() == true) {
            viewModelScope.launch {

                val peopleService = PeopleService.getInstance()
                try {

                    var page = 0

                    do {
                        page = page + 1

                        var _peopleWrapper = peopleService.getPeople(page)
                        val _workingList = _people.value ?: emptyList()
                        val _updatedList = _workingList + _peopleWrapper.results
                        _people.value = _updatedList.sortedBy { it.name }
                    } while ( _peopleWrapper.next != null)

                } catch (e: Exception) {

                }
            }
        }
    }

}