package com.example.gweather.data.restapi

import com.example.gweather.models.OpenWeatherResponse
import com.example.gweather.models.UserInfo
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&exclude={part}&appid={key}")
    suspend fun getOpenWeather(
        @Path("lat") lat: String,
        @Path("lon") long: String,
        @Path("part") excludedPart: String? = "",
        @Path("key") apiKey: String
    ) : OpenWeatherResponse


}