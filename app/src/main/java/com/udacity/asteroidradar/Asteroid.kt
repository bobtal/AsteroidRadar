package com.udacity.asteroidradar

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "asteroids_table")
@Parcelize
data class Asteroid(
        @PrimaryKey
        @ColumnInfo(name = "asteroid_id")
        val id: Long,

        val codename: String,

        @ColumnInfo(name = "close_approach_date")
        val closeApproachDate: String,

        @ColumnInfo(name = "absoulute_magnitude")
        val absoluteMagnitude: Double,

        @ColumnInfo(name = "estimated_diameter")
        val estimatedDiameter: Double,

        @ColumnInfo(name = "relative_velocity")
        val relativeVelocity: Double,

        @ColumnInfo(name = "distance_from_earth")
        val distanceFromEarth: Double,

        @ColumnInfo(name = "is_potentially_hazardous")
        val isPotentiallyHazardous: Boolean
        ) : Parcelable