package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.udacity.asteroidradar.Asteroid

@Dao
interface AsteroidDatabaseDao {

    @Insert
    fun insertAsteroid(asteroid: Asteroid)

    @Update
    fun updateAsteroid(asteroid: Asteroid)

    @Query("SELECT * FROM asteroids_table WHERE asteroid_id = :id")
    fun getAsteroid(id: Long) : Asteroid

    @Query("DELETE FROM asteroids_table")
    fun clear()

    @Query("SELECT * FROM asteroids_table ORDER BY close_approach_date")
    fun getAsteroids() : LiveData<List<Asteroid>>
}