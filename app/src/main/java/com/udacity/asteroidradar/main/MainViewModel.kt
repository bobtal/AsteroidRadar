package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.network.NasaApi
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception
import javax.security.auth.callback.Callback

class MainViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    private val _asteroidList = MutableLiveData<List<Asteroid>>()
    val asteroidList : LiveData<List<Asteroid>>
        get() = _asteroidList

    var asteroids = listOf(
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, false),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, false),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, false),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, false),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, false),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, false),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, false),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, false),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, false),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),
            Asteroid(123L, "testOne", "123456", 123.0, 123.0, 123.0, 123.0, true),

    )

    private val _navigateToAsteroidDetails = MutableLiveData<Asteroid?>()
    val navigateToAsteroidDetails : LiveData<Asteroid?>
        get() = _navigateToAsteroidDetails

    init {
        getAsteroids()
    }

    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToAsteroidDetails.value = asteroid
    }

    fun onAsteroidDetailsNavigated() {
        _navigateToAsteroidDetails.value = null
    }

    private fun getAsteroids()  {
        viewModelScope.launch {
            try {
                val result = parseAsteroidsJsonResult(JSONObject(NasaApi.retrofitService.getAsteroids(apiKey = "jobvxkJnhTNndxj7sL4AK1HuqxmZ3sYGUmeypXGM")))
                _asteroidList.value = result
                Log.d("MainViewModel", "Yay, data fetched!")
            } catch (e: Exception) {
                Log.d("MainViewModel", "problem: " + e.message ?: "Problem fetching data")
            }
        }
    }
}