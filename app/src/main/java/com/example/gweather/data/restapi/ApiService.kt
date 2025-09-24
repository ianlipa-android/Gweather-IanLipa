package com.example.gweather.data.restapi

import com.example.gweather.models.OpenWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/data/2.5/data")
    suspend fun getOpenWeather(
        @Query("lat") lat: String,
        @Query("lon") long: String,
        @Query("exclude") excludedPart: String = "minutely,hourly",
        @Query("appid") apiKey: String
    ) : Response<OpenWeatherResponse>


}