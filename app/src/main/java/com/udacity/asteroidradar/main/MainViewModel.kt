package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.ImageOfTheDay
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.network.NasaApi
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception

enum class NasaApiStatus { LOADING, ERROR, DONE }

class MainViewModel : ViewModel() {

    private val _status = MutableLiveData<NasaApiStatus>()
    val status : LiveData<NasaApiStatus>
        get() = _status

    private val _asteroidList = MutableLiveData<List<Asteroid>>()
    val asteroidList : LiveData<List<Asteroid>>
        get() = _asteroidList

    private val _imageOfTheDay = MutableLiveData<ImageOfTheDay>()
    val imageOfTheDay : LiveData<ImageOfTheDay>
        get() = _imageOfTheDay

    private val _navigateToAsteroidDetails = MutableLiveData<Asteroid?>()
    val navigateToAsteroidDetails : LiveData<Asteroid?>
        get() = _navigateToAsteroidDetails

    init {
        getAsteroids()
        getImageOfTheDay()
    }

    private fun getAsteroids()  {
        viewModelScope.launch {
            _status.value = NasaApiStatus.LOADING
            try {
                val result = parseAsteroidsJsonResult(JSONObject(NasaApi.retrofitService.getAsteroids(apiKey = "jobvxkJnhTNndxj7sL4AK1HuqxmZ3sYGUmeypXGM")))
                _asteroidList.value = result
                _status.value = NasaApiStatus.DONE
            } catch (e: Exception) {
                Log.d("MainViewModel", "problem: " + e.message ?: "Problem fetching data")
                _status.value = NasaApiStatus.ERROR
            }
        }
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