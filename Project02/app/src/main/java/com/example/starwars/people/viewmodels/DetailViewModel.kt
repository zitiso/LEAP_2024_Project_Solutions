package com.example.starwars.people.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.people.data.Film
import com.example.starwars.people.data.Person
import com.example.starwars.people.services.FilmService
import com.example.starwars.people.services.PeopleService
import com.example.starwars.people.services.PlanetService
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val _person : MutableLiveData<Person> = MutableLiveData(Person.empty())
    val person get() = _person

    val regex = """\d+""".toRegex()

    fun getPerson( id :Int ) {

        viewModelScope.launch {
            val peopleService = PeopleService.getInstance()
            val planetService = PlanetService.getInstance()
            val filmService = FilmService.getInstance()

            try {

                val tempPerson = peopleService.getPerson(id)

                // retrieve the homeworld
                val homeworldId: Int? = regex.find(tempPerson.homeworld)?.value?.toInt()
                val planet = homeworldId?.let { planetService.getPlanet(homeworldId) }
                if (planet != null) {
                    tempPerson.homeworld = planet.name
                }

                val filmList :MutableList<Film> = mutableListOf()

                // retrieve the films
                tempPerson.films?.forEach {
                    url -> run {
                        val filmId: Int? = regex.find(url)?.value?.toInt()
                        val film = filmId?.let { filmService.getFilm(filmId) }

                        film?.let { filmList.add(it) }

                    }
                }

                // sort the films by episode
                filmList.sortBy { it.episode_id }

                // build a list of film titles in this format: "Episode IV: A New Hope'
                val filmTitles :MutableList<String> = mutableListOf()

                filmList.forEach {
                    filmTitles.add("""Episode ${toRomanNumeral(it.episode_id)}: ${it.title}""")
                }

                tempPerson.films = filmTitles

                _person.value = tempPerson

            } catch (e: Exception) {

            }
        }

    }

}

fun toRomanNumeral( number :Int ) :String {

    var returnVal = ""

    val romanNumerals: List<String> = listOf(
        "0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"
    )

    if(number >= 0 && number <= 9) {
        returnVal = romanNumerals[number]
    }

    return returnVal

}