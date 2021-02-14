package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.network.NasaApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepository(private val database: AsteroidDatabase) {

    val asteroids: LiveData<List<Asteroid>> = database.asteroidDatabaseDao.getAsteroids()

        suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            val asteroids = parseAsteroidsJsonResult(
                JSONObject(
                    NasaApi.retrofitService.getAsteroids()))
            database.asteroidDatabaseDao.insertAll(*asteroids.toTypedArray())
        }
    }
}