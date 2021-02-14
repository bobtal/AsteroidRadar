package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.udacity.asteroidradar.Asteroid

@Dao
interface AsteroidDatabaseDao {

    @Query("SELECT * FROM asteroids_table ORDER BY close_approach_date")
    fun getAsteroids() : LiveData<List<Asteroid>>

    @Query("SELECT * FROM asteroids_table")
    fun getAllAsteroids() : LiveData<List<Asteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: Asteroid)
}