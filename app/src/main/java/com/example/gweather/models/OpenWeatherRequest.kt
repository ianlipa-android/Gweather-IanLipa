package com.example.gweather.models


//@Entity
data class OpenWeatherRequest(
    val lat: Double,
    val lon: Double,
    //val timezone: String,
    val apiKey: String
)
