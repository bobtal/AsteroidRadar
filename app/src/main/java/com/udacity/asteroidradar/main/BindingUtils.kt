package com.udacity.asteroidradar.main

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R

@BindingAdapter("asteroidIcon")
fun ImageView.setAsteroidIcon(item: Asteroid?) {
    item?.let {
        setImageResource(when (item.isPotentiallyHazardous) {
            true -> R.drawable.ic_status_potentially_hazardous
            else -> R.drawable.ic_status_normal
        })
    }
}