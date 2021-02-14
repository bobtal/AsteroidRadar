package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.ImageOfTheDay
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase.Companion.getInstance
import com.udacity.asteroidradar.network.NasaApi
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _imageOfTheDay = MutableLiveData<ImageOfTheDay>()
    val imageOfTheDay : LiveData<ImageOfTheDay>
        get() = _imageOfTheDay

    private val _navigateToAsteroidDetails = MutableLiveData<Asteroid?>()
    val navigateToAsteroidDetails : LiveData<Asteroid?>
        get() = _navigateToAsteroidDetails

    private val database = getInstance(application)
    private val asteroidRepository = AsteroidRepository(database)

    val asteroidList = asteroidRepository.asteroids

    init {
        viewModelScope.launch {
            asteroidRepository.refreshAsteroids()
        }
        getImageOfTheDay()
    }

    private fun getImageOfTheDay() {
        viewModelScope.launch {
            try {
                _imageOfTheDay.value = NasaApi.retrofitService
                        .getImageOfTheDay("jobvxkJnhTNndxj7sL4AK1HuqxmZ3sYGUmeypXGM")
                Log.d("MainViewModel", imageOfTheDay.toString())
            } catch (e: Exception) {
                Log.d("MainViewModel", "problem: " + e.message ?: "Problem fetching data")
            }
        }
    }

    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToAsteroidDetails.value = asteroid
    }

    fun onAsteroidDetailsNavigated() {
        _navigateToAsteroidDetails.value = null
    }
}