package com.example.gweather.models

import com.example.gweather.models.currentweather.OpenWeatherCurrentResponse

data class UserInfo(
    val username: String,
    val password: String,
    val weatherList: List<OpenWeatherCurrentResponse>? = null
)