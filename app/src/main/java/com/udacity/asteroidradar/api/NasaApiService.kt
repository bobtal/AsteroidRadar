package com.udacity.asteroidradar.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.ImageOfTheDay
import com.udacity.asteroidradar.api.getNextSevenDaysFormattedDates
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "jobvxkJnhTNndxj7sL4AK1HuqxmZ3sYGUmeypXGM"

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface NasaApiService {
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
            @Query("start_date") startDate: String = getNextSevenDaysFormattedDates()[0],
            @Query("end_date") endDate: String = getNextSevenDaysFormattedDates()[7],
            @Query("api_key") apiKey: String = API_KEY
    ) : String

    @GET("planetary/apod")
    suspend fun getImageOfTheDay(@Query("api_key") apiKey: String) : ImageOfTheDay
}

object NasaApi {
    val retrofitService : NasaApiService by lazy {
        retrofit.create(NasaApiService::class.java)
    }
}