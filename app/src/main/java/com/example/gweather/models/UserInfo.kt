package com.example.gweather.models

data class UserInfo(
    val username: String,
    val password: String,
    val weatherList: List<OpenWeatherCurrentResponse>? = null
)