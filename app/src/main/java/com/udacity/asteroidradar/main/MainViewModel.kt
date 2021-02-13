package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.Asteroid

class MainViewModel : ViewModel() {
    val asteroids = listOf(
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

    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToAsteroidDetails.value = asteroid
    }

    fun onAsteroidDetailsNavigated() {
        _navigateToAsteroidDetails.value = null
    }
}